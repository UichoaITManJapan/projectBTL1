package ra.entity;

import ra.IEntity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static ra.run.Library.*;


public class Book implements IEntity, Serializable {
    private String id; // mã sách
    private String title; // tiều đề sách
    private String author; // tác giả
    private String publisher; // nhà sản xuất
    private int year; // năm xuất bản
    private String description; // Mô tả sách
    private int catalogId; // mã thể loại sách


    public Book() {
    }

    public Book(String id, String title, String author, String publisher, int year, String description, int catalogId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.catalogId = catalogId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    @Override
    public void input() {
        // Validate Book
        this.id = validateBookId();
        this.title = validateBookTitle();
        this.author = validateBookAuthor();
        this.publisher = validateBookPusblisher();
        this.year = validateBookYear();
        this.description = validateBookDescription();
    }

    //Validate bookId
    public String validateBookId() {
        System.out.println("Nhập vào mã sách :");
        do {
            String inputBookId = scanner.nextLine();
            if (inputBookId != null || inputBookId.trim().length() != 0) {
                try {
                    String bookId = inputBookId;
                    if (bookId.startsWith("B") && bookId.length() == 4) {
                        boolean isExistBookId = false;
                        for (Book book : bookList) {
                            if (book.getId().equals(bookId)) {
                                isExistBookId = true;
                                break;
                            }
                        }
                        if (isExistBookId) {
                            System.err.println("Mã sách đã tồn tại. Vui lòng nhập lại!");
                        } else {
                            return bookId;
                        }
                    } else {
                        System.err.println("Mã sách phải bắt đầu là 'B' và phải có 4 ký tự. Vui longf nhập lại!");
                    }
                } catch (Exception e) {
                    System.err.println("Xảy ra lỗi. Vui lòng liên hệ ");
                }
            } else {
                System.err.println("Mã sách không được để trống. Vui lòng nhập mã sách!");
            }
        } while (true);
    }

    // Validate bookTitle
    public static String validateBookTitle() {
        System.out.println("Nhập vào tiêu đề sách :");
        do {
            String inputBookTitle = scanner.nextLine();
            if (inputBookTitle != null || inputBookTitle.trim().length() != 0) {
                try {
                    String bookTitle = inputBookTitle;
                    boolean existBookTitle = false;
                    for (Book book : bookList) {
                        if (book.getTitle().equals(bookTitle)) {
                            existBookTitle = true;
                            break;
                        }
                    }
                    if (existBookTitle) {
                        System.err.println("Tiêu đề sách đã tồn tại. Vui lòng nhập lại!");
                    } else {
                        if (bookTitle.length() >= 6 && bookTitle.length() <= 50) {
                            return bookTitle;
                        } else {
                            System.err.println("Tiêu đề sách phải từ 6 đến 50 ký tự. Vui lòng nhập lại!");
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Xảy ra lỗi trong quá trình nhập dữ liệu. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Tiêu đề sách không được để trống. Vui lòng nhập lại!");
            }
        } while (true);
    }

    public static String validateBookAuthor() {
        System.out.println("Nhập vào tên tác giả :");
        do {
            String inputBookAuthor = scanner.nextLine().trim();
            if (!inputBookAuthor.isEmpty()) {
                return inputBookAuthor;
            } else {
                System.err.println("Tên tác giả không được để trống. Vui lòng nhập vào tên tác giả!");
            }
        } while (true);
    }

    // validate bookPublisher
    public static String validateBookPusblisher() {
        System.out.println("Nhập vào tên nhà xuất bản :");
        do {
            String inputBookPublisher = scanner.nextLine().trim();
            if (!inputBookPublisher.isEmpty()) {
                return inputBookPublisher;
            } else {
                System.err.println("Nhà xuất bản không được để trống. Vui lòng nhập vào tên nhà xuất bản!");
            }
        } while (true);
    }

    public static int validateBookYear() {
        System.out.println("Nhập vào năm xuất bản :");
        do {
            String inputBookYear = scanner.nextLine();
            if (inputBookYear != null || inputBookYear.trim().length() != 0) {
                try {
                    int bookYear = Integer.parseInt(inputBookYear);
                    LocalDate currentDate = LocalDate.now();
                    int currentYear = currentDate.getYear();
                    if (bookYear >= 1970 && bookYear <= currentYear) {
                        return bookYear;
                    } else {
                        System.err.println("Năm xuất bản phải tối thiểu từ năm 1970 và không lớn hơn năm " + currentYear + ". Vui lòng nhập lại!");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Năm xuất bản phải là số nguyên. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("Năm xuất bản không được để trống. Vui lòng nhập vào năm xuất bản!");
            }
        } while (true);
    }

    // validate bookDescription
    public static String validateBookDescription() {
        System.out.println("Nhập vào mô tả sách :");
        do {
            String inputBookDes = scanner.nextLine().trim();
            if (!inputBookDes.isEmpty()) {
                return inputBookDes;
            } else {
                System.err.println("Mô tả sách không được bỏ trống. Vui lòng nhập mô tả sách!");
            }
        } while (true);
    }

    @Override
    public void output() {
        String categoryName = categoryList.stream().filter(category -> category.getId() == this.catalogId).collect(Collectors.toList()).get(0).getName();
        System.out.println();
        DecimalFormat df = new DecimalFormat("| %-10s | %-38s | %-16s | %-14s | %-5d | %-16s | %-16s |");
        System.out.format("+------------+----------------------------------------+------------------+----------------+-------+------------------+------------------+");
        System.out.format("|  Mã sách   |                Tiêu đề                 |  　　Tác  giả     |　Nhà xuất bản　 |  Năm  |    Mô tả sách    |    Thể loại      |");
        System.out.format("+------------+----------------------------------------+------------------+----------------+-------+------------------+------------------+");
        System.out.printf("| %-10s | %-38s | %-16s | %-14s | %-5d | %-16s | %-16s |\n",
                id,truncateString(title,38),truncateString(author,16),truncateString(publisher,14),year,truncateString(description,16),truncateString(categoryName,16));
        System.out.format("+------------+----------------------------------------+------------------+----------------+-------+------------------+------------------+");
    }
    private String truncateString(String s,int maxLength){
        if (s.length() <= maxLength){
            return s;
        } else {
            return s.substring(0,maxLength -3) + "...";
        }
    }
}
