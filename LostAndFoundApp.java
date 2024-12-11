import java.util.*;


// Kelas Item untuk menyimpan data barang yang hilang atau ditemukan
class Item {
    String name;
    String category;
    String description;
    boolean isLost;

    public Item(String name, String category, String description, boolean isLost) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.isLost = isLost;
    }

    @Override
    public String toString() {
        return "Nama: " + name + "\nKategori: " + category + "\nDeskripsi: " + description + "\n";
    }
}
// ======= Anggota 1 : Menangani Materi Bubble Sorting =======
// ======= Anggota 2: Menangani Materi Searching Sort Colection =======
class Sorting {    
    public static void sortItemsByName(Map<String, Item> itemsMap) {
        // Mengubah map menjadi list untuk proses pengurutan
        List<Item> itemsList = new ArrayList<>(itemsMap.values());
    
        // Implementasi Bubble Sort
        int n = itemsList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Membandingkan nama barang secara leksikografis
                if (itemsList.get(j).name.compareTo(itemsList.get(j + 1).name) > 0) {
                    // Menukar item jika urutannya salah
                    Item temp = itemsList.get(j);
                    itemsList.set(j, itemsList.get(j + 1));
                    itemsList.set(j + 1, temp);
                }
            }
        }
    
        // Menampilkan barang yang sudah diurutkan
        System.out.println("Barang setelah diurutkan berdasarkan nama:");
        for (Item item : itemsList) {
            System.out.println(item);
        }
    }
    
}


class Searching {
    public static void searchAndShow(String name, Map<String, Item> lostItemsMap, Map<String, Item> foundItemsMap) {
        // Konversi nama menjadi huruf kecil untuk pencarian case-insensitive
        String lowerCaseName = name.toLowerCase();
    
        // Linear search untuk barang hilang
        for (Map.Entry<String, Item> entry : lostItemsMap.entrySet()) {
            if (entry.getKey().toLowerCase().equals(lowerCaseName)) {
                System.out.println("Barang Hilang Ditemukan:");
                System.out.println(entry.getValue());
                return;
            }
        }
    
        // Linear search untuk barang ditemukan
        for (Map.Entry<String, Item> entry : foundItemsMap.entrySet()) {
            if (entry.getKey().toLowerCase().equals(lowerCaseName)) {
                System.out.println("Barang Ditemukan Ditemukan:");
                System.out.println(entry.getValue());
                return;
            }
        }
    
        // Jika tidak ditemukan
        System.out.println("Barang tidak ditemukan.");
    }
    
}

// ======= Anggota 3: Menangani Materi Linked List =======
class ItemNode {
    Item item;
    ItemNode next;

    ItemNode(Item item) {
        this.item = item;
        this.next = null;
    }
}

class ItemList {
    ItemNode head;

    public void addItem(Item item) {
        ItemNode newItem = new ItemNode(item);
        if (head == null) {
            head = newItem;
        } else {
            ItemNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }
    }

    public void displayItems() {
        ItemNode temp = head;
        while (temp != null) {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }
}

// ======= Anggota 4: Menangani Materi Binary Tree =======
class TreeNode {
    Item item;
    TreeNode left, right;

    public TreeNode(Item item) {
        this.item = item;
        left = right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public void insert(Item item) {
        root = insertRec(root, item);
    }

    private TreeNode insertRec(TreeNode root, Item item) {
        if (root == null) {
            root = new TreeNode(item);
            return root;
        }
        if (item.name.compareTo(root.item.name) < 0) {
            root.left = insertRec(root.left, item);
        } else if (item.name.compareTo(root.item.name) > 0) {
            root.right = insertRec(root.right, item);
        }
        return root;
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.item);
            inorderRec(root.right);
        }
    }

    public int countItems() {
        return countRec(root);
    }

    private int countRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countRec(root.left) + countRec(root.right);
    }

    public void clear() {
        root = null;
    }

    public Item search(String name) {
        return searchRec(root, name);
    }

    private Item searchRec(TreeNode root, String name) {
        if (root == null) {
            return null;
        }
        if (root.item.name.equalsIgnoreCase(name)) {
            return root.item;
        }
        if (name.compareToIgnoreCase(root.item.name) < 0) {
            return searchRec(root.left, name);
        }
        return searchRec(root.right, name);
    }

    // Menghapus item dari Binary Tree
    public void delete(String name) {
        root = deleteRec(root, name);
    }

    private TreeNode deleteRec(TreeNode root, String name) {
        if (root == null) {
            return root;
        }
        if (name.compareToIgnoreCase(root.item.name) < 0) {
            root.left = deleteRec(root.left, name);
        } else if (name.compareToIgnoreCase(root.item.name) > 0) {
            root.right = deleteRec(root.right, name);
        } else {
            // Node dengan satu anak atau tidak ada anak
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node dengan dua anak: ambil nilai terkecil dari subtree kanan
            root.item = minValue(root.right);
            root.right = deleteRec(root.right, root.item.name);
        }
        return root;
    }

    private Item minValue(TreeNode root) {
        Item minv = root.item;
        while (root.left != null) {
            minv = root.left.item;
            root = root.left;
        }
        return minv;
    }

    // Menampilkan tinggi Binary Tree
    public int height() {
        return heightRec(root);
    }

    private int heightRec(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(heightRec(root.left), heightRec(root.right));
    }

    // Menampilkan item dalam level order
    public void levelOrder() {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.item);
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}

// ======= Anggota 5: Menangani Materi Graph DFS =======
class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addEdge(String source, String destination) {
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());
        adjacencyList.get(source).add(destination);
    }

    public void dfs(String start) {
        Set<String> visited = new HashSet<>();
        dfsUtil(start, visited);
    }

    private void dfsUtil(String vertex, Set<String> visited) {
        if (visited.contains(vertex)) return;
        visited.add(vertex);
        System.out.println(vertex);
        for (String neighbor : adjacencyList.get(vertex)) {
            dfsUtil(neighbor, visited);
        }
    }
}

// ======= Anggota 6: Menangani Tampilan Menu Utama =======
class LostAndFoundSystem {
    Map<String, Item> lostItemsMap;
    Map<String, Item> foundItemsMap;

    public LostAndFoundSystem() {
        lostItemsMap = new HashMap<>();
        foundItemsMap = new HashMap<>();
    }

    // Menambahkan barang hilang
public void addLostItem(String name, String category, String description, BinaryTree lostTree) {
    Item item = new Item(name, category, description, true);
    lostItemsMap.put(name, item); // Simpan di HashMap
    lostTree.insert(item); // Simpan di BinaryTree
}

// Menambahkan barang ditemukan
public void addFoundItem(String name, String category, String description, BinaryTree foundTree) {
    Item item = new Item(name, category, description, false);
    foundItemsMap.put(name, item); // Simpan di HashMap
    foundTree.insert(item); // Simpan di BinaryTree
}


    // Menampilkan semua barang hilang
    public void printLostItems() {
        System.out.println("Daftar Barang Hilang:");
        for (Item item : lostItemsMap.values()) {
            System.out.println(item);
        }
    }

    // Menampilkan semua barang ditemukan
    public void printFoundItems() {
        System.out.println("Daftar Barang Ditemukan:");
        for (Item item : foundItemsMap.values()) {
            System.out.println(item);
        }
    }

    // Menambahkan hubungan antar barang dalam graf
    public void addRelationBetweenItems(String name1, String name2, Graph graph) {
        graph.addEdge(name1, name2);
    }
}


// Kelas utama LostAndFoundApp
public class LostAndFoundApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LostAndFoundSystem system = new LostAndFoundSystem();
        BinaryTree lostTree = new BinaryTree();
        BinaryTree foundTree = new BinaryTree();
        Graph graph = new Graph();

        while (true) {
            System.out.println("\n===== Sistem Lost and Found =====");
            System.out.println("1. HashMap");
            System.out.println("2. Graph (DFS)");
            System.out.println("3. Collection");
            System.out.println("4. Linked List");
            System.out.println("5. Binary Tree");
            System.out.println("6. Bubble Sort");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline setelah angka

            if (choice == 1) {
                // Menu HashMap
                System.out.println("\n=== Pemilihan Berdasarkan HashMap ===");
                System.out.println("1. Tambah Barang Hilang ke HashMap");
                System.out.println("2. Tambah Barang Ditemukan ke HashMap");
                System.out.println("3. Tampilkan Barang Hilang");
                System.out.println("4. Tampilkan Barang Ditemukan");
                System.out.print("Pilih opsi: ");
                int hashChoice = scanner.nextInt();
                scanner.nextLine();

                if (hashChoice == 1) {
                    // Tambah Barang Hilang ke HashMap
                    System.out.print("Masukkan nama barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan kategori barang: ");
                    String category = scanner.nextLine();
                    System.out.print("Masukkan deskripsi barang: ");
                    String description = scanner.nextLine();
                    system.addLostItem(name, category, description, lostTree);
                } else if (hashChoice == 2) {
                    // Tambah Barang Ditemukan ke HashMap
                    System.out.print("Masukkan nama barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan kategori barang: ");
                    String category = scanner.nextLine();
                    System.out.print("Masukkan deskripsi barang: ");
                    String description = scanner.nextLine();
                    system.addFoundItem(name, category, description, foundTree);
                } else if (hashChoice == 3) {
                    system.printLostItems();
                } else if (hashChoice == 4) {
                    system.printFoundItems();
                }

            } else if (choice == 2) {
                // Menu Graph
                System.out.println("\n=== Pemilihan Berdasarkan Graph ===");
                System.out.println("1. Tambah Hubungan Antar Barang");
                System.out.println("2. Lakukan DFS");
                System.out.print("Pilih opsi: ");
                int graphChoice = scanner.nextInt();
                scanner.nextLine();

                if (graphChoice == 1) {
                    System.out.print("Masukkan nama barang pertama: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Masukkan nama barang kedua: ");
                    String name2 = scanner.nextLine();
                    system.addRelationBetweenItems(name1, name2, graph);
                } else if (graphChoice == 2) {
                    System.out.print("Masukkan nama barang awal untuk DFS: ");
                    String start = scanner.nextLine();
                    graph.dfs(start);
                }

            } else if (choice == 3) {
                // Menu Sorting/Search Collection
                System.out.println("\n=== Pemilihan Berdasarkan Collection ===");
                System.out.println("1. Urutkan Barang Hilang Berdasarkan Nama");
                System.out.println("2. Cari Barang Berdasarkan Nama");
                System.out.print("Pilih opsi: ");
                int sortSearchChoice = scanner.nextInt();
                scanner.nextLine();

                if (sortSearchChoice == 1) {
                    System.out.println("Mengurutkan Barang Hilang:");
                    Sorting.sortItemsByName(system.lostItemsMap);
                } else if (sortSearchChoice == 2) {
                    System.out.print("Masukkan nama barang yang dicari: ");
                    String name = scanner.nextLine();
                    Searching.searchAndShow(name, system.lostItemsMap, system.foundItemsMap);
                }

            } else if (choice == 4) {
                // Menu Linked List
                System.out.println("\n=== Pemilihan Berdasarkan Linked List ===");
                ItemList linkedList = new ItemList();
                System.out.println("1. Tambah Barang ke Linked List");
                System.out.println("2. Tampilkan Barang di Linked List");
                System.out.print("Pilih opsi: ");
                int listChoice = scanner.nextInt();
                scanner.nextLine();

                if (listChoice == 1) {
                    System.out.print("Masukkan nama barang: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan kategori barang: ");
                    String category = scanner.nextLine();
                    System.out.print("Masukkan deskripsi barang: ");
                    String description = scanner.nextLine();
                    linkedList.addItem(new Item(name, category, description, true));
                } else if (listChoice == 2) {
                    linkedList.displayItems();
                }

            } else if ( choice == 5) {
                // Menu Binary Tree
                System.out.println("\n=== Pemilihan Berdasarkan Binary Tree ===");
                System.out.println("1. Tampilkan Barang Hilang Secara Terurut");
                System.out.println("2. Cari Barang di Binary Tree");
                System.out.println("3. Hitung Total Barang di Binary Tree");
                System.out.println("4. Hapus Barang dari Binary Tree"); // Opsi baru
                System.out.println("5. Tampilkan Tinggi Binary Tree"); // Opsi baru
                System.out.println("6. Tampilkan Barang dalam Level Order"); // Opsi baru
                System.out.print("Pilih opsi: ");
                int treeChoice = scanner.nextInt();
                scanner.nextLine();
            
                if (treeChoice == 1) {
                    System.out.println("Daftar Barang Hilang (Terurut):");
                    lostTree.inorder();
                } else if (treeChoice == 2) {
                    System.out.print("Masukkan nama barang yang dicari: ");
                    String name = scanner.nextLine();
                    Item item = lostTree.search(name);
                    if (item != null) {
                        System.out.println("Barang ditemukan:");
                        System.out.println(item);
                    } else {
                        System.out.println("Barang tidak ditemukan.");
                    }
                } else if (treeChoice == 3) {
                    // Hitung total barang di Binary Tree
                    int totalItems = lostTree.countItems();
                    System.out.println("Total barang di Binary Tree: " + totalItems);
                } else if (treeChoice == 4) {
                    System.out.print("Masukkan nama barang yang ingin dihapus: ");
                    String name = scanner.nextLine();
                    lostTree.delete(name);
                    System.out.println("Barang dengan nama " + name + " telah dihapus (jika ada).");
                } else if (treeChoice == 5) {
                    int height = lostTree.height();
                    System.out.println("Tinggi Binary Tree: " + height);
                } else if (treeChoice == 6) {
                    System.out.println("Barang dalam Level Order:");
                    lostTree.levelOrder();
                }

            } else if (choice == 6) {
                // Menu Bubble Sort
                System.out.println("\n=== Bubble Sort  ===");
                
                // Membuat salinan dari lostItemsMap untuk diurutkan
                Map<String, Item> itemsToSort = new HashMap<>(system.lostItemsMap);
                
                if (system.lostItemsMap.isEmpty()) {
                    System.out.println("Barang tidak ada.");
                } else {
                    System.out.println("Mengurutkan Barang Hilang dengan Bubble Sort:");
                    Sorting.sortItemsByName(itemsToSort);
                }

            } else if (choice == 7) {
                // Keluar
                System.out.println("Terima kasih telah menggunakan sistem Lost and Found!");
                break;
            } else {
                System.out.println("Opsi tidak valid. Coba lagi.");
            }
        }
        scanner.close();
    }
}
