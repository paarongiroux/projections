
import java.lang.Math;

public class Mops
{

  private double[][] projection = {{1.0, 0.0, 0.0},
                                   {0.0, 1.0, 0.0}};


  public Mops()
  {

  }

  public double[][] scale(double[][] matrix, double scalar)
  {
    double[][] outMatrix = new double[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++)
    {
      for (int j = 0; j < matrix[i].length; j++)
      {
        outMatrix[i][j] = matrix[i][j] * scalar;
      }
    }
    return outMatrix;
  }

  public double[][] shiftX(double[][] matrix, double scalar)
  {
    double[][] outMatrix = new double[matrix.length][matrix[0].length];
    for (int i = 0; i < matrix.length; i++)
    {
      for (int j = 0; j < matrix[i].length; j++)
      {
        if (j == 0)
        {
          outMatrix[i][j] = matrix[i][j] + scalar;
        }
        else
        {
          outMatrix[i][j] = matrix[i][j];
        }
      }
    }
    return outMatrix;
  }

  // Multiplys matrix A with matrix B
  public double[][] multiply(double[][] matrixA, double[][] matrixB)
  {
    int rowsInd;
    int colsInd;
    int rowsLimit =  matrixA.length;
    int colsLimit = matrixB[0].length;
    double[][] outMatrix = new double[rowsLimit][colsLimit];

    for (rowsInd = 0; rowsInd < matrixA.length; rowsInd++)
    {
      for (colsInd = 0; colsInd < matrixB[0].length; colsInd++)
      {
        outMatrix[rowsInd][colsInd] =
            calculateValue(rowsInd, colsInd, matrixA, matrixB);
      }
    }

    return outMatrix;
  }

  // helper function computes crossproduct to be used in matrix multiplication
  private double calculateValue(int row, int col, double[][] matrixA, double matrixB[][])
	{
		int indexA;

		double totalVal = 0;

		for(indexA = 0; indexA < matrixA[row].length ; indexA++)
		{
			totalVal += matrixA[row][indexA] * matrixB[indexA][col];
		}
		return totalVal;
	}

  // performs orthographic projection on a 3d matrix.
  public double[][] project2D(double[][] matrix)
  {
    double[][] out = new double[matrix.length][2];
    double[][] temp = new double[3][1];
    double[][] projected;

    for (int i = 0; i < matrix.length; i++)
    {
      temp[0][0] = matrix[i][0];
      temp[1][0] = matrix[i][1];
      temp[2][0] = matrix[i][2];

      projected = multiply(projection, temp);

      out[i][0] = projected[0][0];
      out[i][1] = projected[1][0];
    }
    return out;
  }


  // TO DO: add support for rotating about a point, not (0,0,0)
  // Rotates matrix on the X axis by angle degrees
  public double[][] rotateX(double[][] matrix, double angle)
  {
    angle = Math.toRadians(angle);
    double[][] out = new double[matrix.length][matrix[0].length];
    double[][] temp = new double[3][1];
    double[][] rotated;
    double[][] rotation = {{ 1, 0, 0},
                            { 0, Math.cos(angle), -1 * Math.sin(angle)},
                            { 0, Math.sin(angle), Math.cos(angle)}};

  for (int i = 0; i < matrix.length; i++)
  {
    temp[0][0] = matrix[i][0];
    temp[1][0] = matrix[i][1];
    temp[2][0] = matrix[i][2];

    rotated = multiply(rotation, temp);

    out[i][0] = rotated[0][0];
    out[i][1] = rotated[1][0];
    out[i][2] = rotated[2][0];

  }
  return out;
  }

  // Rotates matrix on the Y axis by angle degrees
  public double[][] rotateY(double[][] matrix, double angle)
  {
    angle = Math.toRadians(angle);
    double[][] out = new double[matrix.length][matrix[0].length];
    double[][] temp = new double[3][1];
    double[][] rotated;
    double[][] rotation = {{ Math.cos(angle), 0, Math.sin(angle)},
                            { 0, 1, 0},
                            { -1 * Math.sin(angle), 0, Math.cos(angle)}};

    for (int i = 0; i < matrix.length; i++)
    {
      temp[0][0] = matrix[i][0];
      temp[1][0] = matrix[i][1];
      temp[2][0] = matrix[i][2];

      rotated = multiply(rotation, temp);

      out[i][0] = rotated[0][0];
      out[i][1] = rotated[1][0];
      out[i][2] = rotated[2][0];

    }
    return out;
  }

  // Rotates matrix on the Z axis by angle degrees
  public double[][] rotateZ(double[][] matrix, double angle)
  {
    angle = Math.toRadians(angle);
    double[][] out = new double[matrix.length][matrix[0].length];
    double[][] temp = new double[3][1];
    double[][] rotated;
    double[][] rotation = {{ Math.cos(angle), -1 * Math.sin(angle), 0},
                            { Math.sin(angle), Math.cos(angle), 0},
                            { 0, 0, 1}};
    for (int i = 0; i < matrix.length; i++)
    {
      temp[0][0] = matrix[i][0];
      temp[1][0] = matrix[i][1];
      temp[2][0] = matrix[i][2];

      rotated = multiply(rotation, temp);

      out[i][0] = rotated[0][0];
      out[i][1] = rotated[1][0];
      out[i][2] = rotated[2][0];

    }
    return out;
  }


  // prints contents of matrix
  public void print(double[][] matrix)
  {

    for (int i = 0; i < matrix.length; i++)
    {
      System.out.print("[ ");
      for (int j = 0; j < matrix[i].length; j++)
      {
        System.out.print(" " + matrix[i][j]);
      }
      System.out.println("]");
    }
  }


}
