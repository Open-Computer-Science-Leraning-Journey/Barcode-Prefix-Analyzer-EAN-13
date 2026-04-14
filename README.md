# Supply Chain Parser

Dự án này được tạo ra với mục đích phục vụ cho bản thân tôi, cụ thể khi tôi nhìn vào _barcode_ và tôi muốn tìm hiểu xem đằng sau _barcode_ đó có ý nghĩa gì và nó hoạt động ra sao.

Chương trình của tôi chỉ focus vào EAN-13 vì đây là mã phổ thông tôi gặp thường ngày cũng như các sản phẩm ma tôi thường dùng cũng sử dụng mã này nhiều nhất.

## Mô tả

Một chương trình nhỏ phân tích mã vạch dựa trên tiêu chuẩn GS1. Hệ thống không chỉ kiểm tra định dạng mà còn thực hiện xác thực thực thông qua logic thông qua thuật toán số kiểm tra (Check Digit) và truy xuất nguồn gốc xuất xứ.

Tuy nhiên barcode chỉ là mã vạch định danh sản phẩm, nó không xác định được sản phẩm là thật hay giả thông qua barcode.

## Tính năng

- Validation: Xác thực định dạng mã vạch 13 chữ số bằng Regex.

- Integrity Check: Áp dụng thuật toán Modulo 10 để xác thực số kiểm tra (Check Digit).

- Origin Lookup: Tự động xác định quốc gia sản xuất dựa trên cơ sở dữ liệu tiền tố GS1.

- Clean Architecture: Sử dụng Facade Pattern để cung cấp giao diện đơn giản cho người dùng và Dependency Injection để quản lý các thành phần hệ thống.

## Yêu cầu

- Java 21+
- Maven 3.9+

## Cài đặt và chạy

1. Clone dự án

```bash
git clone https://github.com/Open-Computer-Science-Leraning-Journey/Supply-Chain-Parser.git
```

2. Build bằng Maven

```bash
mvn clean install
```

3. Chạy ứng dụng

```bash
mvn exec:java -Dexec.mainClass="com.peregrine.Main"
```

## Ví dụ output

```bash
8935049101109
Barcode: 8935049101109
Country: Vietnam
```

## Cấu trúc Project

Dự án được cấu trúc theo mô hình phân lớp để dễ bảo trì và mở rộng.

```bash
src/main/java/com/peregrine/
├── docs/              # Tài liệu ghi chú trong quá trình phát triển
├── facade/            # Lớp giao diện (BarcodeScanner) - Điểm tiếp xúc duy nhất cho Client.
├── processor/         # Lớp điều khiển chính (BarcodeProcessor) - Phối hợp các logic.
├── model/             # Các Data Objects (Barcode, BarcodeResponse).
├── service/           # Logic nghiệp vụ (GS1PrefixService).
├── validator/         # Logic kiểm tra (BarcodeValidator).
└── Main.java          # Điểm khởi tạo (Bootstrap) và chạy ứng dụng.
```
