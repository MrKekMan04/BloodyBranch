package com.mrkekman04.bloodybranch.gui.component;

public abstract class DrawTooltip {
    protected int x;
    protected int y;

    public abstract boolean isBound(int mouseX, int mouseY);

    public static class Square extends DrawTooltip {
        private final int height;
        private final int width;

        public Square(int x, int y, int height, int width) {
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
        }

        @Override
        public boolean isBound(int mouseX, int mouseY) {
            return mouseX > x && mouseY > y && mouseX < x + width && mouseY < y + height;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}