package ra.run;

import ra.color.ColorManager;
import ra.entity.Book;
import ra.entity.Category;
import ra.file.FileManager;

import java.util.Comparator;
import static ra.run.Library.categoryList;
import static ra.run.Library.bookList;
import static ra.run.Library.scanner;
public class CatalogMenu {
    public static void catalogMenu() {
            // đọc dữ liệu từu file
            do {
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.println("⎜                        QUẢN LÝ THỂ LOẠI                        ⎜");
                System.out.println("┼✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧┼");
                System.out.println("⎜ ➣ 1. ⎜    Thêm mới thể loại                                    ⎜");
                System.out.println("⎜ ➣ 2. ⎜    Hiển thị danh sách theo tên A – Z                    ⎜");
                System.out.println("⎜ ➣ 3. ⎜    Thống kê thể loại và số sách có trong mỗi thể loại   ⎜");
                System.out.println("⎜ ➣ 4. ⎜    Cập nhật thể loại                                    ⎜");
                System.out.println("⎜ ➣ 5. ⎜    Xóa thể loại                                         ⎜");
                System.out.println("⎜ ➣ 6. ⎜    Quay lại                                             ⎜");
                System.out.println("✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧✧");
                System.out.print(ColorManager.YELLOW + "  ➣ Chọn : \t\t " + ColorManager.RESET);
                try {
                    int choiceCatalog = Integer.parseInt(scanner.nextLine());
                    switch (choiceCatalog){
                        case 1:
                            addCatalog();
                            break;
                        case 2:
                            displayCatalogASC();
                            break;
                        case 3:
                            staticCatalog();
                            break;
                        case 4:
                           updateCatalog();
                           break;
                        case 5:
                            deleteCatalog();
                            break;
                        case 6:
                            FileManager.writeDataToCategoryFile(categoryList);
                            Library.libraryMenu();
                        default:
                            System.err.println("Vui lòng chọn từ 1 đến 6!");
                    }
                } catch (NumberFormatException ex1){
                    System.err.println("Lỗi định dạng kiểu chữ. Vui lòng nhập lại!");
                }
            } while (true);
        }
        //Thêm mới thể loại sách
        public static void addCatalog(){
            System.out.println("Nhập vào số lượng thể loại :");
            do {
                try {
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        System.out.println("✦✦✦✦✦✦✦✦✦✦✦ Nhập thông tin thể loại sách thứ " + (i + 1) + " ✦✦✦✦✦✦✦✦✦✦✦");
                        Category category = new Category();
                        category.input();
                        categoryList.add(category);
                    }
                    System.out.println();
                    System.out.println();
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi đinh dạng kiểu số nguyên. Vui lòng nhập lại!");
                } catch (Exception exception){
                    System.err.println("Xảy ra lỗi. VUi lòng liên hệ tới hệ thống.");
                }
            }while (true);
        }
        // Hiển thị thể loại sách theo thứ tự A-Z
        public static void displayCatalogASC(){
        categoryList.stream().sorted(Comparator.comparing(Category::getName)).forEach(category -> category.output());
            System.out.println();
            System.out.println();
        }
        //Thống kê thể loại và số sách có trong mỗi thể loại
        public static void staticCatalog(){
            System.out.println("✧✧✧✧✧✧✧✧✧✧✧THỐNG KÊ THỂ LOẠI VÀ SỐ SÁCH✧✧✧✧✧✧✧✧✧✧✧✧✧");
            for (Category category : categoryList) {
        int cntBook = (int) bookList.stream().filter(book -> book.getCatalogId() == category.getId()).count();
                System.out.println("\tThể loại : " + category.getName() + " - Số sách : " + cntBook);
            }
            System.out.println();
            System.out.println();
        }
        //Cập nhật thể loại
    public static void updateCatalog(){
        // Hiển thị danh sách thể loại để người dùng chọn
        displayCatalogASC();
        // nhập vào mã thể loại cần cập nhật
        System.out.println("Nhập vào mã thể loại cần cập nhât :");
        int updateCatalogId = Integer.parseInt(scanner.nextLine());
        boolean isExistCatalogId = false;
        for (Category cat: categoryList) {
            if (cat.getId() == updateCatalogId){
                isExistCatalogId = true;
                cat.setName(Category.validateCatalogName());
                cat.setStatus(Category.validateCatalogStatus());
                break;
            }
        }
        if (!isExistCatalogId){
            System.err.println("Không tìm thấy mã thể loại cần cập nhật.");
        } else {
            System.out.println("Đã cập nhật thành công ✔︎");
        }
        System.out.println();
        System.out.println();
    }
    public static void deleteCatalog(){
        //Hiển thị danh sách thể loại cho người dùng xoá
        displayCatalogASC();
        //nhập mã thể loại cần xoá
        System.out.println("Nhập vào mã thể loại cần xoá :");
        int catalogIdDel = Integer.parseInt(scanner.nextLine());
        boolean isHasBooksReferencingCategory = false;
        for (Book book: bookList) {
            if (book.getCatalogId() == catalogIdDel){
                isHasBooksReferencingCategory = true;
                break;
            }
        }
        if (isHasBooksReferencingCategory){
            System.err.println("Thể loaị có sách. Không thế xoá thể loại này.");
        } else{
            boolean isCheckCatalogDel = false;
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId() == catalogIdDel){
                    isCheckCatalogDel = true;
                    categoryList.remove(i);
                    break;
                }
            }
            if (!isCheckCatalogDel){
                System.err.println("Không tìm thấy thể loại cần xoá ❗");
            } else {
                System.out.println("Đã xoá thể loại " + catalogIdDel +" thành công ✓");
            }
        }
        System.out.println();
        System.out.println();
    }
}

