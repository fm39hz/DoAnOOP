# Kitchen Manager
  Đồ án môn lập trình hướng đối tượng của nhóm 4

## Thành viên
  - Phạm Danh Hiển
  - Nguyễn Thạc Trung Hiếu
  - Ngô Tiến Long
  - Nông Quang Tùng
  - Nguyễn Khắc Nhật
## Đề tài
  - Viết chương trình quản lý nhà bếp
## Thiết lập hệ thống
### Windows
  - Đảm bảo hệ thống đã cài đủ các dependencies:
    - [Maven](https://maven.apache.org/download.cgi)
    - [JDK20](https://www.oracle.com/java/technologies/java-se-glance.html)
  - Thêm Maven vào PATH

### Linux (Arch based)
  - Cài bằng terminal
    - ```sudo pacman -S java-openjdk```
    - ```sudo pacman -S maven```

## Thiết lập project
  - Sau khi clone từ git về, import database kitchen.sql vào hệ db, sau đó chạy lệnh sau bằng terminal: 
    - ```mvn clean install```