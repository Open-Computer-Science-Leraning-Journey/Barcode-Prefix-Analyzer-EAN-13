# Hướng dẫn

Đây là file hướng dẫn cho bản thân tôi về khởi tạo một dự án maven

## Command

```bash
mvn archetype:generate \
  -DgroupId=com.yourname.parser \
  -DartifactId=supply-chain-parser \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DinteractiveMode=false
```

**Câu lệnh trên có ý nghĩa là gì**

- `mvn archetype:generate`: gọi plugin `archetype` và thực thi mục tiêu `generate`. Đây là lệnh bắt đầu quá trình tạo dự án từ template.

- `-DgroupId`: tên cá nhân hoặc tổ chức tạo ra dự án, quy ước sẽ đặt theo tên miền ngược.

- `DartifactId`: tên dự án, cũng là tên file JAR khi đóng gói

- `DarchetypeArtifactId`: Loại khuôn mẫu muốn sử dụng, `maven-archetype-quickstart` là một khuôn mẫu đơn giản nhất, các loại khuôn mẫu còn lại sẽ được tiếp cận khi học sâu hơn.

- `DinteractiveMode=false`: chế độ tương tác, nếu chỉ định là `true`, Maven sẽ dừng lại hỏi xác nhận phiên bản, tên gói ..., nếu giá trị là `false` giúp lệnh chạy thẳng từ đầu đến cuối theo tham số đã truyền.

**Câu lệnh trên hoạt động thế nào**

- **Bước 1:** Tìm kiếm Archetype - Maven kiểm tra trong Local Reponsitory (thư mục `.m2`) xem đã có template quickstart chưa. Nếu chưa, nó sẽ lên **Maven Central Reponsitory** để tải về.

- **Bước 2:** Xác định cấu trúc - Dựa vào `groupId` và `artifactId`, nó sẽ tạo thư mục như ví dụ sau:
  - `com.peregrine.parser` $Rightarrow$ `src/main/java/com/peregrine/parser/`

- **Bước 3:** Tạo ra file `pom.xml`, file này dùng để khai báo và thiết lập các dependency cơ bản, mặc định sẽ có JUnit.

- **Bước 4:** Tạo cấu trúc thư mục chuẩn

```bash
 .
├──  pom.xml
├── 󰂺 README.md
└── 󰣞 src
    ├──  Documentation
    │   └──  Instruction.md
    ├──  main
    │   └──  java
    │       └──  com
    │           └──  peregrine
    │               └──  parser
    │                   └──  App.java
    └──  test
        └──  java
            └──  com
                └──  peregrine
                    └──  parser
                        └──  AppTest.java
```

**Tại sao lại sử dụng các câu lệnh này**

- Đảm bảo tính đồng nhất
- Tuân thủ quy ước, mọi lập trình viên Java đều có thể biết code nằm ở đâu, tài nguyên ở đâu mà không cần giải thích.
- Tự động hóa thay vì tạo từng file thủ công, lệnh này thường tích hợp các script CI/CD hoặc các tool khởi tạo dự án tự động.
- Quản lý dependency

## Sử dụng Maven để biên dịch

Maven là gì? Maven là công cụ quản lý và tự động hóa việc xây dựng dự án (Build Automation Tool) dành cho Java.

Maven có 3 vai trò cốt lõi mà nó đảm nhiệm:

- Quản lý thư viện, khai báo trong file `pom.xml`
- Chuẩn hóa cấu trúc dự án, cấu trúc đã đưa chi tiết ở mục trên.
- Vòng đời xây dựng, Maven có các công cụ như sau:
  - `validate`: kiểm tra xem thông tin dự án có đúng không
  - `compile`: Biên dịch code Java sang Byte Code
  - `test`: Chạy các bài kiểm tra tự động (do dev viết)
  - `package`: Đóng gói thành file `.jar` hoặc `.war`
  - `install`: Đưa các file đã đóng gói vào kho lưu trữ cục bộ trên máy
  - `deploy`: Đẩy file lên máy chủ hoặc kho lưu trữ của công ty.

> [!NOTE]
> Các phase trong Maven chay theo thứ tự cộng dồn, nếu ta chạy `mvn íntall` nó sẽ chạy tuần tự như sau:
> `validate` $\rightarrow$ `compile` $\rightarrow$ `test` $\rightarrow$ `package` $\rightarrow$ `verify` $\rightarrow$ `install`
