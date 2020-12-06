package day_05;

public class BinaryBoarding {
  private int row;
  private int column;

  private BinaryBoarding(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public static BinaryBoarding convertFromSpecification(String specification) {
    String rowSpecification = specification.substring(0, 7);
    String columnSpecification = specification.substring(7);

    boolean[] rowBinaryArray = new boolean[7];
    for (int k = 0; k < 7; k++) {
      char rowLetter = rowSpecification.charAt(k);
      rowBinaryArray[k] = rowLetter == 'B' ? true : false;
    }

    int rowNumber = 0;
    rowNumber += rowBinaryArray[0] ? 64 : 0;
    rowNumber += rowBinaryArray[1] ? 32 : 0;
    rowNumber += rowBinaryArray[2] ? 16 : 0;
    rowNumber += rowBinaryArray[3] ? 8 : 0;
    rowNumber += rowBinaryArray[4] ? 4 : 0;
    rowNumber += rowBinaryArray[5] ? 2 : 0;
    rowNumber += rowBinaryArray[6] ? 1 : 0;

    boolean[] columnBinaryArray = new boolean[3];
    for (int k = 0; k < 3; k++) {
      char rowLetter = columnSpecification.charAt(k);
      columnBinaryArray[k] = rowLetter == 'R' ? true : false;
    }

    int columnNumber = 0;
    columnNumber += columnBinaryArray[0] ? 4 : 0;
    columnNumber += columnBinaryArray[1] ? 2 : 0;
    columnNumber += columnBinaryArray[2] ? 1 : 0;

    return new BinaryBoarding(rowNumber, columnNumber);
  }

  public int getSeatId() {
    return row * 8 + column;
  }
}
