import java.util.*;

public class JalanTercepat {
    static class Koordinat {
        int x, y;
        Koordinat(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Titik {
        Koordinat koor;
        List<String> jalan;
        Titik(Koordinat koor, List<String> jalan) {
            this.koor = koor;
            this.jalan = new ArrayList<>(jalan);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> map = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("OK")) {
            map.add(line);
        }

        int baris = map.size();
        int kolom = map.get(0).length();
        Koordinat start = null, end = null;

        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                if (map.get(i).charAt(j) == '^') {
                    start = new Koordinat(i, j);
                } else if (map.get(i).charAt(j) == '*') {
                    end = new Koordinat(i, j);
                }
            }
        }

        if (start == null || end == null) {
            System.out.println("Posisi Anne atau rumah Januari tidak ditemukan.");
            return;
        }

        String[] arah = {"atas", "bawah", "kiri", "kanan"};
        int[][] langkah = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] lewat = new boolean[baris][kolom];
        Queue<Titik> queue = new LinkedList<>();
        queue.add(new Titik(start, new ArrayList<>()));
        lewat[start.x][start.y] = true;

        while (!queue.isEmpty()) {
            Titik current = queue.poll();
            Koordinat koor = current.koor;
        
            if (koor.x == end.x && koor.y == end.y) {
                if (!current.jalan.isEmpty()) {
                    String currentDirection = current.jalan.get(0);
                    int count = 1;
                    int totalCount = 0;
        
                    for (int i = 1; i < current.jalan.size(); i++) {
                        if (current.jalan.get(i).equals(currentDirection)) {
                            count++;
                        } else {
                            System.out.println(count + " " + currentDirection);
                            totalCount += count;
                            currentDirection = current.jalan.get(i);
                            count = 1;
                        }
                    }
                    System.out.println(count + " " + currentDirection);
                    totalCount += count;
        
                    System.out.println(totalCount + "Langkah");
                }
                return;
            }
        
            for (int i = 0; i < 4; i++) {
                int newX = koor.x + langkah[i][0];
                int newY = koor.y + langkah[i][1];
                if (newX >= 0 && newX < baris && newY >= 0 && newY < kolom && !lewat[newX][newY] && map.get(newX).charAt(newY) != '#') {
                    lewat[newX][newY] = true;
                    List<String> newPath = new ArrayList<>(current.jalan);
                    newPath.add(arah[i]);
                    queue.add(new Titik(new Koordinat(newX, newY), newPath));
                }
            }
        }
        
        System.out.println("tidak ada");
    }
}
