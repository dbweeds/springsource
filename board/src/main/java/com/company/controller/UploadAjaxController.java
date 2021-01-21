package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.FileAttach;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Slf4j
public class UploadAjaxController {
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("ajax 업로드 폼 요청");
	}

	@PostMapping(value = "/uploadAjax", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<FileAttach>> uploadPost(MultipartFile[] uploadFile) {
		log.info("업로드 요청");

		String uploadFolder = "F:\\upload";
		String uploadFileName = null;

		// 폴더 생성
		String uploadFolderPath = getFolder();// 2021\\01\\19
		File uploadPath = new File(uploadFolder, uploadFolderPath);// d:\\upload\\2021\\01\\19

		if (!uploadPath.exists()) {
			uploadPath.mkdirs();
		}

		// 첨부 파일에 대한 정보를 담을 객체 생성
		List<FileAttach> attachList = new ArrayList<>();

		for (MultipartFile f : uploadFile) {
			log.info("---------------------------");
			log.info("upload file name : " + f.getOriginalFilename());
			log.info("upload file size : " + f.getSize());

			// 파일명 중복 해결
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + f.getOriginalFilename();

			FileAttach attach = new FileAttach();
			attach.setFileName(f.getOriginalFilename());
			attach.setUploadPath(uploadFolderPath);
			attach.setUuid(uuid.toString());

			File saveFile = new File(uploadPath, uploadFileName);

			try {
				// 이미지인지 일반 파일인지 확인
				if (checkImageType(saveFile)) {
					attach.setFileType(true);
					// 이미지라면 썸네일로 한번 더 저장
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					InputStream in = f.getInputStream();
					Thumbnailator.createThumbnail(in, thumbnail, 100, 100);
					in.close();
					thumbnail.close();
				}
				// 서버에 저장
				f.transferTo(saveFile);
				attachList.add(attach);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}

		}
		return new ResponseEntity<List<FileAttach>>(attachList, HttpStatus.OK);
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("썸내일요청" + fileName);
		File f = new File("F:\\upload\\" + fileName);

		ResponseEntity<byte[]> entity = null;

		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Type", Files.probeContentType(f.toPath()));// image/jpg
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(f), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}

	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Resource> download(String fileName) {
		log.info("다운로드 요청 : " + fileName);

		Resource resource = new FileSystemResource("F:\\upload\\" + fileName);

		// uuid값 제거 후 파일 다운로드 하기
		String resourceName = resource.getFilename().substring(resource.getFilename().indexOf("_") + 1);
		log.info(resourceName);

		HttpHeaders headers = new HttpHeaders();

		try {
			headers.add("Content-Disposition",
					"attachment;filename=" + new String(resourceName.getBytes("utf-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	// 서버에서 파일 삭제
	@PostMapping("/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName, String type) {
		try {
			File file = new File("F:\\upload\\" + URLDecoder.decode(fileName, "utf-8"));

			// 파일(썸네이ㄹ,일반파일)삭제
			file.delete();

			if (type.equals("image")) {
				String oriName = file.getAbsolutePath().replace("s_", "");
				file = new File(oriName);
				file.delete();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);

	}

	// 서버에 저장한 파일이 이미지인지 일반 파일인지 확인
	private boolean checkImageType(File file) {// ~/txt=> text/plain, text/html,image/jpeg,image/png

		/*
		 * String contentType = Files.probeContentType(file.toPath()); return
		 * contentType.startsWith("image");
		 */
		MimetypesFileTypeMap m = new MimetypesFileTypeMap();
		m.addMimeTypes("image png jpg jpeg gif");
		return m.getContentType(file).contains("image");

	}

	// 날짜에 따라 폴더 생성하기
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
		// 폴더 구분시 사용하는 문자 - windows \,리눅스 /->File.separator == 운영체제에따라 저걸 붙혀줌
	}
}
