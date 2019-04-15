import java.util.ArrayList;

public class MathTask {

    /*
     * Работа метода реализована с помощью обратной польской нотации.
     * Сначала генерируются все комбинации в постфиксной нотации, затем решается выражение и проверяется равно ли оно 24
     */

    public boolean canBeEqualTo24(int[] nums) {

        char[] operators = new char[]{'+', '-', '*', '/'};
        ArrayList<String> numbersCombinations = new ArrayList<>();
        ArrayList<String> operationsCombinations = new ArrayList<>();
        /*
         * Цикл ниже создает всевозможные комбинации чисел и знаков
         */
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    for (int l = 0; l < 4; l++) {
                        if (i != j && i != k && i != l && j!= k && j!= l && k!= l) {
                            numbersCombinations.add("" + nums[i] + nums[j] + nums[k] + nums[l]);
                        }
                    }
                    operationsCombinations.add("" + operators[i] + operators[j] + operators[k]);
                }
            }
        }
        ArrayList<String> result = new ArrayList<>();
        /*
         * Цикл ниже объединяет всевозможные комбинации чисел и знаков
         * Существует 4 возможные комбинации генерации выражения для четырёх чисел:
         * xxzxzxz, xxxzzxz, xxxxzzz, xxzxxzz, где x - число, z - оператор
         */
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 64; j++) {
                StringBuilder temp = new StringBuilder(numbersCombinations.get(i).substring(0, 2));
                temp.append(numbersCombinations.get(i).charAt(2));
                temp.append(operationsCombinations.get(j).charAt(1));
                temp.append(numbersCombinations.get(i).charAt(3));
                temp.append(operationsCombinations.get(j).charAt(2));
                result.add(temp.toString());
            }
            for (int j = 0; j < 64; j++) {
                StringBuilder temp = new StringBuilder(numbersCombinations.get(i).substring(0, 3));
                temp.append(operationsCombinations.get(j).charAt(0));
                temp.append(operationsCombinations.get(j).charAt(1));
                temp.append(numbersCombinations.get(i).charAt(3));
                temp.append(operationsCombinations.get(j).charAt(2));
                result.add(temp.toString());
            }
            for (int j = 0; j < 64; j++) {
                StringBuilder temp = new StringBuilder(numbersCombinations.get(i));
                temp.append(operationsCombinations.get(j).charAt(0));
                temp.append(operationsCombinations.get(j).charAt(1));
                temp.append(operationsCombinations.get(j).charAt(2));
                result.add(temp.toString());
            }
            for (int j = 0; j < 64; j++) {
                StringBuilder temp = new StringBuilder(numbersCombinations.get(i).substring(0, 2));
                temp.append(operationsCombinations.get(j).charAt(0));
                temp.append(numbersCombinations.get(i).substring(2, 4));
                temp.append(operationsCombinations.get(j).charAt(1));
                temp.append(operationsCombinations.get(j).charAt(2));
                result.add(temp.toString());
            }
        }
        /*
         * Цикл ниже вычисляет значение каждого выражения и проверяет равно ли оно 24
         */
        for (int j = 0; j < result.size(); j++) {
            char[] chars = result.get(j).toCharArray();
            ArrayList<Float> operands = new ArrayList<>();
            operands.add((float) Character.getNumericValue(chars[0]));
            operands.add((float) Character.getNumericValue(chars[1]));
            for (int i = 2; i < chars.length; i++) {
                if(Character.isDigit(chars[i])) {
                    operands.add((float) Character.getNumericValue(chars[i]));
                } else {
                    switch (chars[i]) {
                        case '+':
                            operands.set(operands.size() - 2, operands.get(operands.size() - 2) +
                                        operands.get(operands.size() - 1));
                            operands.remove(operands.size() -1);
                            break;
                        case '-':
                            operands.set(operands.size() - 2, operands.get(operands.size() - 2) -
                                        operands.get(operands.size() - 1));
                            operands.remove(operands.size() -1);
                            break;
                        case '*':
                            operands.set(operands.size() - 2, operands.get(operands.size() - 2) *
                                        operands.get(operands.size() - 1));
                            operands.remove(operands.size() -1);
                            break;
                        case '/':
                            operands.set(operands.size() - 2, operands.get(operands.size() - 2) /
                                        operands.get(operands.size() - 1));
                            operands.remove(operands.size() -1);
                            break;
                        default:
                            System.out.println("Error!");
                            break;
                    }
                }
            }
            if (operands.get(0) / 24 - 1 < 0.001 && operands.get(0) / 24 - 1 >= 0){
                System.out.println(result.get(j));
                return true;
            }
        }
        return false;

    }

}