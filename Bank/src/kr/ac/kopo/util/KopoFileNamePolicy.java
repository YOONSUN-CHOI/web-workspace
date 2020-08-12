package kr.ac.kopo.util;

import java.io.File;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class KopoFileNamePolicy implements FileRenamePolicy {
	/*
	 * aa.png 라고하면 aa랑 png 분리
	 * 회사에서는 kopo 이후로 일반적으로 년월일 붙인다.  
	 * kopo????????????.png로 나옴.
	 * 어딘가에 aa.png가 kopo????????????.png 라는거 기억ㅑ
	 * */
	@Override
	public File rename(File f) {
		String name = f.getName();
		String ext = "";
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot); 
		} else {
			ext = "";
		}
		String str = "kopo" + UUID.randomUUID();
													
		File renameFile = new File(f.getParent(), str + ext);
		return renameFile;
	}
}
