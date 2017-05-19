/**
 * Created by theoxola on 2017-03-30.
 */
public class Board {

    /**
     * IMPLEMENTATION TREATING BOARDS AS STRINGS
     */

        // Instance vars
        private String layout;

        // default Constructor
        public Board(){

            this("12345678*");
        }

        // construct from string
        public Board(String layout){

            if (!valid(layout)){
                layout = "12345678*";
            }

            this.layout = layout;

        }

        // validate input
        private Boolean valid(String in){

            if (in.length() != 9){
                return false;
            }

           for (int i = 0; i < 9; i++){

                char c = "12345678*".charAt(i);
                if (!in.contains(String.valueOf(c))){
                    return false;
                }
            }

            // reached iff correct length and correct entries

            return true;
        }

        // generate next steps
        public static Board[] nextBoards(Board b){

            // max of 4 new boards can be generated at each step

            Board[] results = new Board[4];

            // get layout of input board
            String bLayout = b.getLayout();

            // initalise to mirror input board
            for (int i = 0; i < 4; i++){
                results[i] = new Board(bLayout);
            }

            int counter = 0;
            int starPos = bLayout.indexOf("*");
            int starCol = starPos % 3;
            int starRow = starPos / 3;


            // see below for use as placeholder in switching
            char placeholder = 'p';

            switch(starCol){

                case 0:
                    if (starRow == 0){
                        // down one
                        char tmp = bLayout.charAt(3);

                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;


                        // right one
                        tmp = bLayout.charAt(1);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }
                     else if (starRow == 1){
                         // down one
                        char tmp = bLayout.charAt(6);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // up one
                        tmp = bLayout.charAt(0);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // right one
                        tmp = bLayout.charAt(4);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }
                     else if (starRow == 2){
                        // up one
                        char tmp = bLayout.charAt(3);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // right one
                        tmp = bLayout.charAt(7);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }

                    break;


                case 1:
                    if (starRow == 0){

                        // down one
                        char tmp = bLayout.charAt(4);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;


                        // right one
                        tmp = bLayout.charAt(2);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // left one
                        tmp = bLayout.charAt(0);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                    }

                    else if (starRow == 1){

                        // down one
                        char tmp = bLayout.charAt(7);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;


                        // right one
                        tmp = bLayout.charAt(5);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // left one
                        tmp = bLayout.charAt(3);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // up one
                        tmp = bLayout.charAt(1);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }

                    else if (starRow == 2){

                        // up one
                        char tmp = bLayout.charAt(4);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;


                        // right one
                        tmp = bLayout.charAt(8);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // left one
                        tmp = bLayout.charAt(6);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }

                    break;

                case 2:
                    if (starRow == 0){
                        // down one
                        char tmp = bLayout.charAt(5);

                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;


                        // left one
                        tmp = bLayout.charAt(1);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }
                    else if (starRow == 1){
                        // down one
                        char tmp = bLayout.charAt(8);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // up one
                        tmp = bLayout.charAt(2);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // left one
                        tmp = bLayout.charAt(4);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }
                    else if (starRow == 2){
                        // up one
                        char tmp = bLayout.charAt(5);
                        String newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        Board next = new Board(newLayout);
                        results[counter] = next;
                        counter++;

                        // left one
                        tmp = bLayout.charAt(7);
                        newLayout = bLayout.replace(tmp, placeholder);
                        newLayout = newLayout.replace('*', tmp);
                        newLayout = newLayout.replace(placeholder, '*');

                        next = new Board(newLayout);
                        results[counter] = next;
                        counter++;
                    }

                    break;
            }


            return results;
        }

        // getter for layout
        public String getLayout() {
        return layout;
    }

        // setter for layout
        public void setLayout(String layout) {
            this.layout = layout;
        }

    // main for testing purposes
        public static void main(String[] args){
            Board test_def = new Board();
            Board test_lay = new Board("7813456*2");
            Board test_inc1 = new Board("122");
            Board test_inc2 = new Board("777888922");

            System.out.println(test_def.getLayout());
            System.out.println(test_lay.getLayout());
            System.out.println(test_inc1.getLayout());
            System.out.println(test_inc2.getLayout());

            Board[] test_next = nextBoards(test_def);

            System.out.println();

            for (Board b : test_next){
                System.out.println(b.getLayout());
            }

            Board[] test_next2 = nextBoards(test_lay);

            System.out.println();

            for (Board b : test_next2){
                System.out.println(b.getLayout());
            }

        }

    }
