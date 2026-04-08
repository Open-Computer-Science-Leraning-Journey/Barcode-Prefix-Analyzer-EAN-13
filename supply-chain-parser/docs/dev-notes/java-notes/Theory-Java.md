📌 Keyword final: Khi bạn khai báo private final String rawValue, Java sẽ không cho phép bạn gán lại giá trị đó sau khi constructor chạy xong. Nếu bạn thử thêm setRawValue() vào class này và compile, Java sẽ báo lỗi ngay. Đây là cách ngôn ngữ enforce immutability ở compile time, không phải runtime.

**Stack trace**

java.lang.NumberFormatException: For input string: "A"
at java.lang.Integer.parseInt (Integer.java:565)
at com.peregrine.validator.BarcodeValidator.validate (BarcodeValidator.java:20)

Loại lỗi NumberFormatException
Nguyên nhân parseInt gặp ký tự `A` không phải số
Tại dòng 20

Khi nào dùng try-catch và khi nào dùng if-else

Ta cần định nghĩa bình thường là gì và ngoại lệ là gì.

Bình thường trong ngữ cảnh này là có thể dự đoán được, có nghĩa là đầu vào sai do người dùng là có thể dự doán được, ví dụ: 893123444ABC, bỏ trống input, barcode thiếu số. Những thứ này đều dự đoán được nên dùng valiation thông thường. Nhứng thứ không kiểm soát được dungf try catch

"Khi nào dùng Exception?" — Khi gặp tình huống nằm ngoài tầm kiểm soát của chương trình, không phải để xử lý input sai của người dùng. Dùng Exception để control flow bình thường là anti-pattern, gọi là "Exception as flow control".

Những tình huống nằm ngoài tầm kiểm soát của chương trình như:

- đọc file nhưng bị xóa file giữa chừng
- mất kết nối db đột ngột
- hết RAM

📌 Phỏng vấn: "Dependency Injection là gì?" — Thay vì class tự tạo dependency của mình, dependency được truyền từ ngoài vào. Giúp code dễ test, dễ thay thế implementation.
