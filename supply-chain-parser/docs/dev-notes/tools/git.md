📌 Phỏng vấn: Câu hỏi hay gặp — "git mv khác git add/rm như thế nào?". Trả lời: git mv là shortcut làm 2 việc cùng lúc — move file trên filesystem và stage luôn. Kết quả cuối cùng trong commit history giống hệt nhau. Bạn vừa làm thủ công 2 bước mà git mv làm trong 1 bước.

checkout, tạo feature mới, rồi len git merge lại chọn
javaimport static org.junit.jupiter.api.Assertions.assertEquals;
Chỉ import assertEquals thôi. assertTrue đã bị xóa hoàn toàn — đúng, không để import thừa.
📌 Phỏng vấn: "Unused import có hại gì không?" — Không crash, nhưng là code smell. Cho thấy người viết không chú ý. Trong code review thực tế sẽ bị comment ngay.

Giờ commit và merge
Anh đang ở branch test/gs1-prefix-service. Workflow như đã setup:
bashgit add .
git commit -m "test(gs1): add GS1PrefixService unit tests"
git push origin test/gs1-prefix-service
Sau đó tạo PR trên GitHub, squash merge vào main.
