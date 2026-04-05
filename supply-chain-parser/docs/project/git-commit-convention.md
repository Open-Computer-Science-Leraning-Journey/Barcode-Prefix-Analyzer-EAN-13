# Git commit convention

<type>(<scope>): <mô tả ngắn>

type:
feat → tính năng mới
fix → sửa bug
refactor → cải thiện code, không thêm feature
docs → chỉ sửa documentation
test → liên quan đến test
chore → setup, config, không liên quan logic

Ví dụ:
feat(barcode): add EAN-13 checksum validator
fix(parser): correct GS1 prefix lookup for range 890-899
chore(maven): configure Java 21 compiler plugin
