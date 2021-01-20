package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.domain.ProductVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductUplodeController {

//	@GetMapping("/product")
//	public void product() {
//		log.info("product 폼 요청");
//	}

	@ResponseBody
	@PostMapping("/product")
	public String productPost(MultipartFile[] file, ProductVO vo) {
		// vo.setFile(file.getName());
		log.info("product 폼 가져오기 : " + vo);
		log.info("product 폼 가져오기 : " + file[0].getOriginalFilename());
		log.info("product 폼 가져오기 : " + file[1].getOriginalFilename());
		return "success";
	}

//	 @PostMapping("/product") @RequstBody =>제이슨으로 받을때
//	 public ResponseEntity<String> productPost(@RequstBody ProductVO vo) { 
//		 log.info("product 폼 가져오기 : "+vo); 
//		 return vo!=null?
//				 new ResponseEntity<String>("success", HttpStatus.OK):
//					 new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST); 
//	 }
//	 

//	@PostMapping(value = "/product",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public ResponseEntity<List<ProductVO>> productPost(ProductVO vo) {
//		log.info("product 폼 가져오기 : "+vo);
//		List<ProductVO> list = new ArrayList<>();
//		for(int i = 0;i<3;i++) {
//			list.add(vo);
//		}
//		return new ResponseEntity<List<ProductVO>>(list, HttpStatus.OK);
//	}
}
