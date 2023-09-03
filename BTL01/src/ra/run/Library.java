package ra.run;

import ra.color.ColorManager;
import ra.entity.Book;
import ra.entity.Category;
import ra.file.FileManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public  class Library {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Category> categoryList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();
    public static void main(String[] args) {
        categoryList = FileManager.readDataFromCategoryFile();
        bookList = FileManager.readDataFromBookFile();
        libraryMenu();
    }
    // quản lý thư viện
    public static void libraryMenu(){
        do {
            System.out.println();
            System.out.println("☥------------------------------------------------☥");
            System.out.println("⎜                 QUẢN LÝ THƯ VIỆN               ⎜");
            System.out.println("☥------------------------------------------------☥");
            System.out.println("⎜  1.  ⎜          Quản lý Thể loại               ⎜");
            System.out.println("⎜  2.  ⎜          Quản lý Sách                   ⎜");
            System.out.println("⎜  3.  ⎜          Thoát                          ⎜");
            System.out.println("☥------------------------------------------------☥");
            System.out.print(ColorManager.YELLOW +"   Chọn chức năng :\t                         "+ ColorManager.RESET);
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                System.out.println();
                System.out.println();
                switch (choice){
                    case 1:
                        CatalogMenu.catalogMenu();
                        break;
                    case 2:
                       BookMenu.bookMenu();
                       break;
                    case 3:
                        System.exit(0);
                    default:
                        System.err.println("vui lòng chọn chức năng từ 1 đến 3 !");
                }
            } catch (NumberFormatException ex1){
                System.err.println("Lỗi " +ex1.getMessage() + ". Vui lòng nhập lại!");
            } catch (Exception exception){
                System.err.println("Xảy ra lỗi trong quá trình nhập dữ liệu. Vui lòng nhập lại!");
            }
        } while (true);
    }


}
