package FizzBuzz;

public class FizzBuzz {
    public String stringFor(int number) {

        if (number<1 || number > 100){
            throw new IllegalArgumentException();
        }
        if(number % 15 == 0){
            return "FizzBuzz";
        }
        else if (number % 3 == 0 ){
            return "Fizz";
        }
        else if (number % 5 == 0){
            return "Buzz";
        }
        return String.valueOf(number);
    }
}
