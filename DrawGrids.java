import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Grids extends Canvas {

    int width, height, rows, columns;
    List<Point> filledCells = new ArrayList<>();

    Grids(int w, int h, int r, int c) {
        setSize(width = w, height = h);
        rows = r;
        columns = c;
    }

    public void fillCell(int row, int column) {
        filledCells.add(new Point(column, row)); // Store as Point(x, y) where x = column, y = row
        repaint(); // Redraw canvas with all cells
    }

    @Override
    public void paint(Graphics g) {
        width = getSize().width;
        height = getSize().height;

        int htOfRow = height / rows;
        int wdOfRow = width / columns;

        // Draw the grid
        g.setColor(Color.BLACK);
        for (int k = 0; k <= rows; k++) {
            g.drawLine(0, k * htOfRow, width, k * htOfRow);
        }
        for (int k = 0; k <= columns; k++) {
            g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
        }

        // Fill all stored cells
        g.setColor(Color.BLACK);
        for (Point cell : filledCells) {
            g.fillRect(cell.x * wdOfRow, cell.y * htOfRow, wdOfRow, htOfRow);
        }
    }
}

public class DrawGrids extends Frame {

    Grids grid;

    DrawGrids(String title, int w, int h, int rows, int columns) {
        setTitle(title);
        grid = new Grids(w, h, rows, columns);
        add(grid);
        setSize(w, h);
    }

    public void fillCell(int row, int column) {
        grid.fillCell(row, column);
    }

    public void drawCharacterInBinary(char ch, int startRow, int startColumn) {
        String binary = String.format("%8s", Integer.toBinaryString((int) ch)).replace(' ', '0');
        System.out.println("Printing '" + ch + "' as binary " + binary);  // DEBUG
        
        int row = startRow;
        int column = startColumn;
        
        for (int i = 0; i < 8; i++) {
            // Right of 2x4
            if (i % 2 == 0) {
                column = startColumn - 1;
            } else {
                // Left of 2x4
                column = startColumn; 
            }
    
            if (binary.charAt(binary.length() - i - 1) == '1') {
                System.out.println("Filling at (" + row + ", " + column + ")");
                fillCell(row, column);
            }

            // Every 2 loops, go up a row
            if ((i % 2 == 0) && i != 0) {
                System.out.println("2 iterations done. Moving up a layer");
                row = row - 1;
            }
        }
    }
    

    public static void main(String[] args) {
        int width = 26;
        int height = 26;
        DrawGrids frame = new DrawGrids("Draw Grids", 500, 500, width, height);
        frame.setVisible(true);

        // Build our Position Squares

        // Top left 
        // Rows
        for (int i = 1; i < 7; i++) {
            frame.fillCell(1, i);
            frame.fillCell(7, i);
        }
        // Columns
        for (int i = 1; i <= 7; i++) {
            frame.fillCell(i, 1);
            frame.fillCell(i, 7);
        }
        // Center Piece
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                frame.fillCell(i+3,j+3);
            }
        }

        // Bottom left 
        // Rows
        for (int i = 1; i < 7; i++) {
            frame.fillCell(height - 1, i);
            frame.fillCell(height - 7, i);
        }
        // Columns
        for (int i = 19; i <= 25; i++) {
            frame.fillCell(i, 1);
            frame.fillCell(i, 7);
        }
        // Center Piece
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                frame.fillCell(i+21,j+3);
            }
        }

        // Top Right 
        // Rows
        for (int i = height - 7; i < height - 1; i++) {
            frame.fillCell(1, i);
            frame.fillCell(7, i);
        }
        // Columns
        for (int i = 1; i <= 7; i++) {
            frame.fillCell(i, 19);
            frame.fillCell(i, 25);
        }
        // Center Piece
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                frame.fillCell(i+3,j+21);
            }
        }


        
        // Draw Alignment Pattern
        // Rows
        for (int i = 17; i < 22; i++) {
            frame.fillCell(17, i);
            frame.fillCell(21, i);
        }

        // Columns
        for (int i = 18; i < 21; i++) {
            frame.fillCell(i, 17);
            frame.fillCell(i, 21);
        }

        frame.fillCell(19, 19);


        // Draw Timing Strips
        // Top to Bottom
        for (int i = 9; i <= 17; i = i + 2) {
            frame.fillCell(i, 7);
        }

        // Left to Right
        for (int i = 9; i <= 17; i = i + 2) {
            frame.fillCell(7, i);
        }

        // Special Dot
        frame.fillCell(18, 9);



        // Bottom right corner
        // 0100 For bianry encoding
        frame.fillCell(height-1, width-2);

        //www.landoncraft.site -  len(20)'
        // 20 = 00010100

        // w = 01110111
        // . = 00101110
        // l = 01101100
        // a = 01100001
        // n = 01101110
        // d = 01100100
        // o = 01101111
        // c = 01100011
        // r = 01110010
        // f = 01100110
        // t = 01110100
        // s = 01110011
        // i = 01101001
        // e = 01100101

        // www.landoncraft.site = 01110111 01110111 01110111 00101110 01101100 01100001 01101110 01100100 01101111 01101110
        //                        01100011 01110010 01100001 01100110 01110100 00101110 01110011 01101001 01110100 01100101

        // Length of address
        /*
         * 0 0
         * 1 0
         * 1 0
         * 0 0
         */
        frame.fillCell(height - 4, width - 2);
        frame.fillCell(height - 5, width - 2);

        // Draw www
        /* w = 01110111
         * 1 1
         * 1 0
         * 1 1
         * 1 0
         */

        frame.drawCharacterInBinary('w', 14, 14);
        
    }
}
