-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 13, 2022 lúc 09:01 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ql_ch_sach`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `macthd` int(255) NOT NULL,
  `mahd` int(255) NOT NULL,
  `masach` int(255) NOT NULL,
  `sl` int(255) NOT NULL,
  `gia` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`macthd`, `mahd`, `masach`, `sl`, `gia`) VALUES
(1, 5, 18, 2, 369000),
(2, 4, 7, 4, 2300000),
(3, 4, 25, 1, 450000),
(4, 8, 25, 4, 7550000),
(5, 7, 24, 6, 4500000),
(6, 4, 21, 3, 2450000),
(7, 2, 1, 2, 3400000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `mactpn` int(255) NOT NULL,
  `mapn` int(255) NOT NULL,
  `masach` int(255) NOT NULL,
  `sl` int(255) NOT NULL,
  `gia` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`mactpn`, `mapn`, `masach`, `sl`, `gia`) VALUES
(1, 1, 16, 20, 26300000),
(2, 2, 26, 17, 156500000),
(3, 3, 23, 40, 124540000),
(4, 4, 19, 30, 132000000),
(5, 1, 23, 23, 635500000),
(6, 2, 6, 53, 235500000),
(7, 5, 8, 76, 765000000),
(8, 6, 26, 44, 455000000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` int(255) NOT NULL,
  `ngayhd` date NOT NULL,
  `manv` int(255) NOT NULL,
  `gia` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `ngayhd`, `manv`, `gia`) VALUES
(1, '2021-12-14', 4, 900000),
(2, '2021-11-17', 5, 750000),
(3, '2021-10-05', 4, 12000000),
(4, '2021-10-09', 1, 720000),
(5, '2021-11-09', 6, 3250000),
(6, '2021-10-19', 2, 3300000),
(7, '2021-11-18', 3, 1765000),
(8, '2021-10-14', 3, 2345000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `mancc` int(255) NOT NULL,
  `tenncc` varchar(50) NOT NULL,
  `diachi` varchar(50) NOT NULL,
  `sdt` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhacungcap`
--

INSERT INTO `nhacungcap` (`mancc`, `tenncc`, `diachi`, `sdt`) VALUES
(1, 'CTY Phat Dat', '12 Le Loi TPHCM', '0985247894'),
(2, 'CTY TNHH HAHA', '643 Ba Thang Hai TPHCM', '0563928763'),
(3, 'CTY TBAY', '36 Nguyen The Truyen TPHCM', '0352678743'),
(4, 'CTY Halink', '975 Tran Hung Dao TPHCM', '0539852674');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` int(255) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `sdt` varchar(255) NOT NULL,
  `diachi` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `hoten`, `sdt`, `diachi`) VALUES
(1, 'Ta Nguyen Hieu', '0563845264', '35 Ba Thang Hai TPHCM'),
(2, 'Tran Ngoc Hai', '0538266538', '12 Nguyen Chi Thanh TPHCM'),
(3, 'Luu Anh Duc', '0763496542', '44 Ngo Quyen TPHCM'),
(4, 'Quach Dai Duc ', '0653928763', '3 Nguyen Trai TPHCM'),
(5, 'Tieu Tri Kien', '0973482654', '137 Khuong Viet TPHCM'),
(6, 'Le Lien Long', '0756274639', '77 Hoa Binh TPHCM'),
(7, 'Hoang Ngoc Thang', '0647352859', '12 Thoai Ngoc Hau TPHCM'),
(8, 'Truong Phuc Thanh', '0652863458', '66 Lo Sieu TPHCM'),
(9, 'Vo Chi Thien', '0635275698', '34 Ba Hom TPHCM'),
(10, 'Tran Gia Huy', '0648396528', '435 Su Van Hanh TPHCM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `mapn` int(255) NOT NULL,
  `ngaynhap` date NOT NULL,
  `mancc` int(255) NOT NULL,
  `gia` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`mapn`, `ngaynhap`, `mancc`, `gia`) VALUES
(1, '2021-12-13', 3, 35000000),
(2, '2021-12-16', 2, 165000000),
(3, '2021-12-12', 4, 70000000),
(4, '2021-12-06', 2, 3300000),
(5, '2021-12-09', 1, 15600000),
(6, '2021-12-07', 3, 23450000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `manv` int(255) NOT NULL,
  `tk` varchar(50) NOT NULL,
  `mk` varchar(50) NOT NULL,
  `quyen` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`manv`, `tk`, `mk`, `quyen`) VALUES
(1, 'b', 'b', '0'),
(5, 'a', 'a', '1'),
(7, 'hoangngocthang', 'hoangngocthangdeptrai', '1'),
(8, 'truongphucthanh', 'truongphucthanhdeptrai', '1'),
(9, 'vochithien', 'vochithiendeptrai', '0'),
(10, 'trangiahuy', 'trangiahuydeptrai', '0');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `masach` int(255) NOT NULL,
  `tensach` varchar(50) NOT NULL,
  `tentg` varchar(50) NOT NULL,
  `nxb` varchar(50) NOT NULL,
  `namxb` int(255) NOT NULL,
  `gia` int(255) NOT NULL,
  `matl` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`masach`, `tensach`, `tentg`, `nxb`, `namxb`, `gia`, `matl`) VALUES
(1, 'Me Vo Khong Loi Ve', 'Chieu Tai Tien Bao', 'NXB Tong Hop TPHCM', 2019, 369000, 3),
(2, 'Than Dao Dan Ton', 'Co Don Dia Phi', 'NXB Kim Dong', 2019, 133000, 1),
(3, 'The Gioi Hoan My', 'Than Dong', 'NXB Tong Hop TPHCM', 2017, 239000, 11),
(4, 'Dau Pha Thuong Khung', 'Thien Tam Tho Dau', 'NXB Thanh Nien', 2019, 255000, 13),
(5, 'Re Quy troi Cho', 'Quy Thuong Nhan', 'NXB Ha Noi', 2020, 675000, 14),
(6, 'Tro Tan', 'Nhat Lan', 'NXB Tong Hop TPHCM', 2020, 1135000, 5),
(7, 'Diem Chi', 'Da Thao', 'NXB Tong Hop TPHCM', 2017, 105000, 1),
(8, 'Noi Chon Binh Yen', 'Da Thao', 'NXB Ha Noi', 2019, 772000, 2),
(9, 'Noi Chon Binh Yen', 'Da Thao', 'NXB Ha Noi', 2019, 772000, 1),
(10, 'Vi Tham Tu Nho Tuoi', 'Hoa Anh Dao', 'NXB Da Nang', 2020, 263000, 2),
(11, 'Phu Nhan Nha Tuong Gia', 'Nguyen Cong', 'NXB Kim DOng', 2019, 136000, 13),
(12, 'Toi Tai Gioi, Ban Cung The', 'Adam Khoo', 'NXB Ha Noi', 2018, 2223000, 2),
(13, 'Dac Nhan Tam', 'Dale Carnegie', 'NXB Tong Hop TPHCM', 2019, 432000, 10),
(14, 'Toi Ac Va Trung Phat', 'Dostoevsky', 'NXB Da Nang', 2018, 472000, 2),
(15, 'Bat Tre Dong Xanh', 'J.D.Salinger', 'NXB Ha Noi', 2019, 72000, 9),
(16, 'Cu Di Roi Se Den', 'Minh DeltaViet', 'NXB Tong Hop TPHCM', 2020, 720000, 2),
(17, 'Toi Tai Gioi, Ban Cung The', 'Adam Khoo', 'NXB Ha Noi', 2018, 2223000, 8),
(18, 'Dac Nhan Tam', 'Dale Carnegie', 'NXB Tong Hop TPHCM', 2019, 432000, 10),
(19, 'Toi Ac Va Trung Phat', 'Dostoevsky', 'NXB Da Nang', 2018, 472000, 2),
(20, 'Bat Tre Dong Xanh', 'J.D.Salinger', 'NXB Ha Noi', 2019, 72000, 12),
(21, 'Cu Di Roi Se Den', 'Minh DeltaViet', 'NXB Tong Hop TPHCM', 2020, 720000, 2),
(22, '7 Thoi Quen De Thanh Dat', 'Stephen R. Covey', 'NXB Tong Hop TPHCM', 2017, 361000, 8),
(23, 'Cuoc Doi Cua Pi', 'Yan Martel', 'NXB Thanh Nien', 2014, 44000, 7),
(24, 'Giet Con Chim Nhai', 'Tom Robinson', 'NXB Thanh Nien', 2016, 178000, 6),
(25, 'Ong Gia Va Bien Ca', 'Ernest Hemingway', 'NXB Tong Hop TPHCM', 2018, 161000, 4),
(26, 'Khong Gia Dinh', 'Hector Malot', 'NXB Van Hoc', 2019, 155000, 2),
(27, 'Chien Binh Cau Vong', 'Andrea Hirata', 'NXB Van Hoc', 2015, 345000, 2),
(28, 'Suoi Nguon', 'Ayn Rand', 'NXB Tien Phong', 2018, 15000, 2),
(29, 'Gatsby Vi Dai', 'Fitzgerald', 'NXB Da Nang', 2021, 154000, 2),
(30, 'a', 'Quy Thuong Nhan', 'NXB Ha Noi', 2020, 675000, 14),
(31, 'b', 'Quy Thuong Nhan', 'NXB Ha Noi', 2020, 675000, 14);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloaisach`
--

CREATE TABLE `theloaisach` (
  `matl` int(255) NOT NULL,
  `tentl` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `theloaisach`
--

INSERT INTO `theloaisach` (`matl`, `tentl`) VALUES
(1, 'Truyen'),
(2, 'Tieu Thuyet'),
(3, 'Giao Trinh'),
(4, 'Thieu Nhi'),
(5, 'Chinh Tri'),
(6, 'Phap Luat'),
(7, 'Khoa Hoc Cong Nghe'),
(8, 'Kinh Te'),
(9, 'Van Hoc'),
(10, 'Nghe Thuat'),
(11, 'Xa Hoi'),
(12, 'Lich Su'),
(13, 'Tam Linh'),
(14, 'Ton Giao');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`macthd`),
  ADD KEY `fk_masach` (`masach`),
  ADD KEY `fk_hoadon` (`mahd`);

--
-- Chỉ mục cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD PRIMARY KEY (`mactpn`),
  ADD KEY `fk_phieunhap` (`mapn`),
  ADD KEY `fk_sach` (`masach`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `fk_nhanvien` (`manv`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`mapn`),
  ADD KEY `fk_nhacungcap` (`mancc`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`manv`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`masach`),
  ADD KEY `fk_theloai` (`matl`);

--
-- Chỉ mục cho bảng `theloaisach`
--
ALTER TABLE `theloaisach`
  ADD PRIMARY KEY (`matl`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `macthd` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  MODIFY `mactpn` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `mahd` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  MODIFY `mancc` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `manv` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  MODIFY `mapn` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `masach` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `theloaisach`
--
ALTER TABLE `theloaisach`
  MODIFY `matl` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `fk_hoadon` FOREIGN KEY (`mahd`) REFERENCES `hoadon` (`mahd`),
  ADD CONSTRAINT `fk_masach` FOREIGN KEY (`masach`) REFERENCES `sach` (`masach`);

--
-- Các ràng buộc cho bảng `chitietphieunhap`
--
ALTER TABLE `chitietphieunhap`
  ADD CONSTRAINT `fk_phieunhap` FOREIGN KEY (`mapn`) REFERENCES `phieunhap` (`mapn`),
  ADD CONSTRAINT `fk_sach` FOREIGN KEY (`masach`) REFERENCES `sach` (`masach`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `fk_nhacungcap` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`);

--
-- Các ràng buộc cho bảng `role`
--
ALTER TABLE `role`
  ADD CONSTRAINT `fk_manv` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `fk_theloai` FOREIGN KEY (`matl`) REFERENCES `theloaisach` (`matl`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
