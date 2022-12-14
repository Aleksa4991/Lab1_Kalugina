import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Задача о миссионерах и каннибалах");
        System.out.println("Выберите номер метода поиска: ");
        System.out.println("\t 1. Поиск в ширину");
        System.out.println("\t 2. Поиск в глубину");
        System.out.print("\n Введите свой выбор и нажмите ENTER: ");

        String optionStr = null; // исключение-перехват
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            optionStr = in.readLine();
        } catch (IOException e) {
            System.out.println("[ERROR] Не удается прочитать введенный параметр.");
            e.printStackTrace();
        }

        int option = Integer.parseInt(optionStr); // проверка на правильность введенных чисел при запуске
        State initialState = new State (3, 3, Position.LEFT, 0, 0);
        switch(option) {
            case 1:
                executeBFS(initialState);
                break;
            case 2:
                executeDLS(initialState);
                break;
            default:
                System.out.println("[ERROR] Недопустимый номер метода.");
        }
    }

    private static void executeBFS(State initialState) {
        BreadthFirstSearch search = new BreadthFirstSearch();
        State solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void executeDLS(State initialState) {
        DepthLimitedSearch search = new DepthLimitedSearch();
        State solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void printSolution(State solution) {
        if (null == solution) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
            List<State> path = new ArrayList<State>();
            State state = solution;
            while(null!=state) {
                path.add(state);
                state = state.getParentState();
            }

            int depth = path.size() - 1;
            for (int i = depth; i >= 0; i--) {
                state = path.get(i);
                if (state.isGoal()) {
                    System.out.print(state.toString());
                } else {
                    System.out.print(state.toString() + " -> ");
                }
            }
            System.out.println("\nDepth: " + depth);
        }
    }
}