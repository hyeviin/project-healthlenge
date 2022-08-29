package khv.healthlenge.util;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import khv.healthlenge.domain.dto.FileData;

public class MyFileUtils {

	
	public static FileData upload(MultipartFile multipartFile, String url) {
		
		String name = multipartFile.getOriginalFilename();
		long size = multipartFile.getSize();
		
		ClassPathResource cpr =	
				new ClassPathResource("static" + url);

		try {
			File location = cpr.getFile();
			multipartFile.transferTo(new File(location, name));
			//location = 경로 , / orgName = 오리지널 네임으로 업로드 할거에요

		} catch (IOException e) {
			e.printStackTrace();
		}

		return FileData.builder()
				.url(url).name(name).size(size)
				.build();
	}

}
