package chap06;

public class Score {
    private char[] answerArr;
    private char[] inputArr;
    public Score(String answer, String input) {
        this.answerArr = answer.toCharArray();
        this.inputArr = input.toCharArray();
    }

    public int balls() {
        String select = "ball";
        return extracted(select);
    }

    public int strikes() {
        String select = "strike";
        return extracted(select);
    }

    private int extracted(String select) {
        int result = 0;
        for (int i = 0;  i < inputArr.length; i++) {
            for (int j = 0; j < answerArr.length; j++) {
                if(select.equals("ball")){
                    if(inputArr[i] == answerArr[j] && i != j) result ++;
                } else {
                    if(inputArr[i] == answerArr[j] && i == j) result ++;
                }
            }
        }
        return result;
    }
}
