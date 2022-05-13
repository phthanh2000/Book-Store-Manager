CREATE TABLE `hoadon`(
  `mahd` varchar(10) NOT NULL,
  `ngayhd` date NOT NULL,
  `manv` varchar(10) NOT NULL,
  `masach` varchar(10) NOT NULL,
  `sl` bigint(255) NOT NULL,
  `gia` bigint(255) NOT NULL	
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `nhacungcap` (
  `mancc` varchar(10) NOT NULL,
  `tenncc` varchar(50) NOT NULL,
  `diachi` varchar(50) NOT NULL,
  `sdt` bigint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` varchar(10) NOT NULL,
  `hoten` varchar(50) NOT NULL,
  `sdt` bigint(10) NOT NULL,
  `tk` varchar(50) NOT NULL,
  `mk` varchar(50) NOT NULL,
  `id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `mapn` varchar(10) NOT NULL,
  `ngaynhap` date NOT NULL,
  `masach` varchar(10) NOT NULL,
  `mancc` varchar(10) NOT NULL,
  `sl` bigint(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `id` varchar(10) NOT NULL,
  `tenquyen` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `masach` varchar(10) NOT NULL,
  `tensach` varchar(50) NOT NULL,
  `tentg` varchar(50) NOT NULL,
  `nxb` varchar(50) NOT NULL,
  `namxb` bigint(10) NOT NULL,
  `gia` bigint(100) NOT NULL,
  `matl` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `theloaisach`
--

CREATE TABLE `theloaisach` (
  `matl` varchar(10) NOT NULL,
  `tentl` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `fk_nhanvien` (`manv`),
  ADD KEY `fk_masach` (`masach`);

--
-- Chỉ mục cho bảng `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`mancc`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `fk_quyen` (`id`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`mapn`),
  ADD KEY `fk_sach` (`masach`),
  ADD KEY `fk_nhacungcap` (`mancc`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

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
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `fk_masach` FOREIGN KEY (`masach`) REFERENCES `sach` (`masach`),
  ADD CONSTRAINT `fk_nhanvien` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `fk_quyen` FOREIGN KEY (`id`) REFERENCES `role` (`id`);

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `fk_nhacungcap` FOREIGN KEY (`mancc`) REFERENCES `nhacungcap` (`mancc`),
  ADD CONSTRAINT `fk_sach` FOREIGN KEY (`masach`) REFERENCES `sach` (`masach`);

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `fk_theloai` FOREIGN KEY (`matl`) REFERENCES `theloaisach` (`matl`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
