Design Principles:

SRP — mỗi class một việc, Barcode giữ data, BarcodeValidator xử lý logic
YAGNI — không tạo field/method khi chưa có use case
DRY — không khai báo compiler version ở 2 chỗ
Immutability — rawValue không được phép thay đổi sau khi tạo
