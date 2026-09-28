**Ngôn ngữ:** [English](README.md) | Tiếng Việt

# Quản Lý Nhân Viên

<p>
  <img src="https://img.shields.io/badge/Java-17%2B-orange" alt="Java">
  <img src="https://img.shields.io/badge/Build-Maven-blue" alt="Maven">
</p>

Ứng dụng Java quản lý thông tin nhân viên, xây dựng bằng **Maven**.

---

## Giới thiệu (About)

Dự án thực hành lập trình hướng đối tượng và cách tổ chức ứng dụng Java thông qua một bài toán nghiệp vụ quen thuộc: quản lý nhân viên. Phục vụ mục đích học tập và luyện tập.

> Ghi chú: danh sách tính năng bên dưới là bản nháp suy ra từ tên và cấu trúc dự án. Bạn chỉnh lại cho khớp với những gì thực sự có trong `src/main`.

---

## Tính năng (dự kiến — chỉnh lại theo code thực tế)

- Thêm nhân viên mới
- Cập nhật thông tin nhân viên
- Xóa nhân viên
- Tìm kiếm nhân viên
- Hiển thị danh sách nhân viên

---

## Công nghệ sử dụng

| Thành phần | Công nghệ |
|---|---|
| Ngôn ngữ | Java 17+ |
| Build tool | Maven |
| IDE | IntelliJ IDEA / Eclipse / VS Code |

---

## Cấu trúc dự án

```
employee-management/
├── .github/modernize/java-upgrade/   # Metadata nâng cấp phiên bản Java
├── src/main/                          # Source code chính
├── pom.xml                            # Cấu hình Maven
└── README.md
```

---

## Bắt đầu

```bash
git clone https://github.com/nhunguy-swe/employee-management.git
cd employee-management
mvn compile
```

Sau đó chạy class chứa hàm `main()` từ IDE, hoặc:

```bash
mvn exec:java -Dexec.mainClass="<ten.package.Main>"
```

---

## Tác giả

- GitHub: [@nhunguy-swe](https://github.com/nhunguy-swe)

---

## Giấy phép

Thực hiện cho mục đích học tập. Bạn có thể tham khảo code để học.
