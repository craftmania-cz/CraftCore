package cz.wake.craftcore.inventory.content;

import com.sk89q.worldguard.internal.flywaydb.core.internal.util.Pair;
import cz.wake.craftcore.inventory.ClickableItem;
import cz.wake.craftcore.inventory.SmartInventory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public interface SlotIterator {

    enum Type {
        HORIZONTAL,
        VERTICAL
    }

    Optional<ClickableItem> get();
    SlotIterator set(ClickableItem item);

    SlotIterator previous();
    SlotIterator next();

    SlotIterator blacklist(int row, int column);

    int row();
    SlotIterator row(int row);

    int column();
    SlotIterator column(int column);

    boolean ended();


    class Impl implements SlotIterator {

        private InventoryContents contents;
        private SmartInventory inv;

        private Type type;
        private int row, column;

        private Set<Pair<Integer, Integer>> blacklisted = new HashSet<>();

        public Impl(InventoryContents contents, SmartInventory inv,
                    Type type, int startRow, int startColumn) {

            this.contents = contents;
            this.inv = inv;

            this.type = type;

            this.row = startRow;
            this.column = startColumn;
        }

        public Impl(InventoryContents contents, SmartInventory inv,
                    Type type) {

            this(contents, inv, type, 0, 0);
        }

        @Override
        public Optional<ClickableItem> get() {
            return contents.get(row, column);
        }

        @Override
        public SlotIterator set(ClickableItem item) {
            contents.set(row, column, item);
            return this;
        }

        @Override
        public SlotIterator previous() {
            if(row == 0 && column == 0)
                return this;

            do {
                switch(type) {
                    case HORIZONTAL:
                        column--;

                        if(column == 0) {
                            column = inv.getColumns() - 1;
                            row--;
                        }
                        break;
                    case VERTICAL:
                        row--;

                        if(row == 0) {
                            row = inv.getRows() - 1;
                            column--;
                        }
                        break;
                }
            }
            while((row != 0 || column != 0) && blacklisted.contains(Pair.of(row, column)));

            return this;
        }

        @Override
        public SlotIterator next() {
            if(ended())
                return this;

            do {
                switch(type) {
                    case HORIZONTAL:
                        column = ++column % inv.getColumns();

                        if(column == 0)
                            row++;
                        break;
                    case VERTICAL:
                        row = ++row % inv.getRows();

                        if(row == 0)
                            column++;
                        break;
                }
            }
            while(!ended() && blacklisted.contains(Pair.of(row, column)));

            return this;
        }

        @Override
        public SlotIterator blacklist(int row, int column) {
            this.blacklisted.add(Pair.of(row, column));
            return this;
        }

        @Override
        public int row() { return row; }

        @Override
        public SlotIterator row(int row) {
            this.row = row;
            return this;
        }

        @Override
        public int column() { return column; }

        @Override
        public SlotIterator column(int column) {
            this.column = column;
            return this;
        }

        @Override
        public boolean ended() {
            return row == inv.getRows() - 1
                    && column == inv.getColumns() - 1;
        }

    }

}
