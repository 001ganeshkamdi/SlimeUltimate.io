import java.util.*;

public class Program04_Interpreter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine().trim());
        sc.nextLine(); // skip blank line

        for (int t = 0; t < cases; t++) {
            int[] ram = new int[1000];
            int[] registers = new int[10];
            Arrays.fill(ram, 0);
            Arrays.fill(registers, 0);

            int ramPtr = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) break;
                ram[ramPtr++] = Integer.parseInt(line.trim());
            }

            int pc = 0, count = 0;
            boolean halted = false;
            while (!halted) {
                int instr = ram[pc];
                int op = instr / 100;
                int d = (instr / 10) % 10;
                int n = instr % 10;
                count++;

                switch (op) {
                    case 1: // halt
                        halted = true;
                        break;
                    case 2: // set register d to n
                        registers[d] = n;
                        pc++;
                        break;
                    case 3: // add n to register d
                        registers[d] = (registers[d] + n) % 1000;
                        pc++;
                        break;
                    case 4: // multiply register d by n
                        registers[d] = (registers[d] * n) % 1000;
                        pc++;
                        break;
                    case 5: // set register d to value of register s
                        registers[d] = registers[n];
                        pc++;
                        break;
                    case 6: // add value of register s to register d
                        registers[d] = (registers[d] + registers[n]) % 1000;
                        pc++;
                        break;
                    case 7: // multiply register d by value of register s
                        registers[d] = (registers[d] * registers[n]) % 1000;
                        pc++;
                        break;
                    case 8: // set register d to value in RAM whose address is in register n
                        registers[d] = ram[registers[n]];
                        pc++;
                        break;
                    case 9: // set value in RAM whose address is in register n to value of register d
                        ram[registers[n]] = registers[d];
                        pc++;
                        break;
                    case 0: // goto location in register d unless register n contains 0
                        if (registers[n] != 0) {
                            pc = registers[d];
                        } else {
                            pc++;
                        }
                        break;
                }
            }

            System.out.println(count);
            if (t < cases - 1) System.out.println();
        }
        sc.close();
    }
}