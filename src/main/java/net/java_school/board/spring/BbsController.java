package net.java_school.board.spring;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.java_school.board.Article;
import net.java_school.board.AttachFile;
import net.java_school.board.Comment;
import net.java_school.board.BoardService;
import net.java_school.commons.PagingHelper;
import net.java_school.commons.WebContants;
import javax.servlet.http.HttpSession;
import net.java_school.user.User;
import net.java_school.commons.WebContants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(
			String boardCd, 
			Integer curPage, 
			String searchWord, 
			Model model,
			HttpSession session) throws Exception {
		
//				//login check
//				User user = (User) session.getAttribute(WebContants.USER_KEY);
//				if (user == null) {
//					return "redirect:/users/login";
//				}

			
		if (boardCd == null) boardCd = "free";
		if (curPage == null) curPage = 1;
		if (searchWord == null) searchWord = "";

		int numPerPage = 10;
		int pagePerBlock = 10;
		
		int totalRecord = boardService.getTotalRecord(boardCd, searchWord);
		
		PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
		
		boardService.setPagingHelper(pagingHelper);
		int start = pagingHelper.getStartRecord();
		int end = pagingHelper.getEndRecord();

		ArrayList<Article> list = boardService.getArticleList(boardCd, searchWord, start, end);
		
		String boardNm = boardService.getBoardNm(boardCd);
		Integer no = boardService.getListNo();
		Integer prevLink = boardService.getPrevLink();
		Integer nextLink = boardService.getNextLink();
		Integer firstPage = boardService.getFirstPage();
		Integer lastPage = boardService.getLastPage();
		int[] pageLinks = boardService.getPageLinks();
		
		model.addAttribute("list", list);
		model.addAttribute("boardNm", boardNm);
		model.addAttribute("no", no);
		model.addAttribute("prevLink", prevLink);
		model.addAttribute("nextLink", nextLink);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("pageLinks", pageLinks);
		model.addAttribute("curPage", curPage);//curPage는 null 값이면 1로 만들어야 하므로
		model.addAttribute("boardCd", boardCd);//boardCd는 null 값이면 free로 만들어야 하므로
		
		//return "bbs/test";
		return "bbs/list";
		
		
	}
	
	@RequestMapping(value="/list2", method={RequestMethod.GET, RequestMethod.POST})
	public String list2(
			String boardCd, 
			Integer curPage, 
			String searchWord, 
			Model model) throws Exception {
			
		if (boardCd == null) boardCd = "free";
		if (curPage == null) curPage = 1;
		if (searchWord == null) searchWord = "";

		int numPerPage = 10;
		int pagePerBlock = 10;
		
		int totalRecord = boardService.getTotalRecord(boardCd, searchWord);
		
		PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);
		
		boardService.setPagingHelper(pagingHelper);
		int start = pagingHelper.getStartRecord();
		int end = pagingHelper.getEndRecord();

		ArrayList<Article> list = boardService.getArticleList(boardCd, searchWord, start, end);
		
		String boardNm = boardService.getBoardNm(boardCd);
		Integer no = boardService.getListNo();
		Integer prevLink = boardService.getPrevLink();
		Integer nextLink = boardService.getNextLink();
		Integer firstPage = boardService.getFirstPage();
		Integer lastPage = boardService.getLastPage();
		int[] pageLinks = boardService.getPageLinks();
		
		model.addAttribute("list", list);
		model.addAttribute("boardNm", boardNm);
		model.addAttribute("no", no);
		model.addAttribute("prevLink", prevLink);
		model.addAttribute("nextLink", nextLink);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("pageLinks", pageLinks);
		model.addAttribute("curPage", curPage);//curPage는 null 값이면 1로 만들어야 하므로
		model.addAttribute("boardCd", boardCd);//boardCd는 null 값이면 free로 만들어야 하므로
		
		return "bbs/bootstrap/dashboard";
		
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Integer articleNo, 
			String boardCd, 
			Integer curPage,
			String searchWord, 
			Model model) throws Exception {

		// 검색어가 null 이면 ""으로 변경	
		if (searchWord == null) searchWord = "";

		// 페이지당 레코드 수 지정
		int numPerPage = 10;
		
		// 페이지 링크의 그룹(block)의 크기 지정
		int pagePerBlock = 10;
		
		/*
		상세보기를 할때마다 조회수를 1 증가
		하단에 목록에서 조회수를 제대로 보기위해서는
		목록 레코드를 페치하기 전에 조회수를 먼저 증가시켜야 한다.
		TODO : 사용자 IP 와 시간을 고려해서 조회수를 증가하도록
		*/
		boardService.increaseHit(articleNo);
		
		//상세보기 
		Article thisArticle = boardService.getArticle(articleNo);
		ArrayList<AttachFile> attachFileList = boardService.getAttachFileList(articleNo);
		ArrayList<Comment> commentList = boardService.getCommentList(articleNo);
		Article nextArticle = boardService.getNextArticle(articleNo, boardCd, searchWord);
		Article prevArticle = boardService.getPrevArticle(articleNo, boardCd, searchWord);
		
		//목록보기
		int totalRecord = boardService.getTotalRecord(boardCd, searchWord);
		PagingHelper pagingHelper = new PagingHelper(totalRecord, curPage, numPerPage, pagePerBlock);

		boardService.setPagingHelper(pagingHelper);
		int start = pagingHelper.getStartRecord();
		int end = pagingHelper.getEndRecord();

		ArrayList<Article> list = boardService.getArticleList(boardCd, searchWord, start, end);
		String boardNm = boardService.getBoardNm(boardCd);
		Integer no = boardService.getListNo();
		Integer prevLink = boardService.getPrevLink();
		Integer nextLink = boardService.getNextLink();
		Integer firstPage = boardService.getFirstPage();
		Integer lastPage = boardService.getLastPage();
		int[] pageLinks = boardService.getPageLinks();
		
		model.addAttribute("thisArticle", thisArticle);
		model.addAttribute("attachFileList", attachFileList);
		model.addAttribute("commentList", commentList);
		model.addAttribute("nextArticle", nextArticle);
		model.addAttribute("prevArticle", prevArticle);

		// 목록을 위한 데이터
		model.addAttribute("list", list);
		model.addAttribute("boardNm", boardNm);
		model.addAttribute("no", no);
		model.addAttribute("prevLink", prevLink);
		model.addAttribute("nextLink", nextLink);
		model.addAttribute("firstPage", firstPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("pageLinks", pageLinks);
		
		return "bbs/view";
	}
	
	@RequestMapping(value="/commentAdd", method=RequestMethod.POST)
	public String commentAdd(Integer articleNo, 
			String boardCd, 
			Integer curPage, 
			String searchWord, 
			String memo) throws Exception {
			
		Comment comment = new Comment();
		comment.setMemo(memo);
		comment.setEmail("bond007@gmail.org");
		comment.setArticleNo(articleNo);
		boardService.insertComment(comment);
		
		searchWord = URLEncoder.encode(searchWord,"UTF-8");
		
		return "redirect:/bbs/view?articleNo=" + articleNo + 
			"&boardCd=" + boardCd + 
			"&curPage=" + curPage + 
			"&searchWord=" + searchWord;

	}
	
	@RequestMapping(value="/commentUpdate", method=RequestMethod.POST)
	public String commentUpdate(Integer commentNo, 
			Integer articleNo, 
			String boardCd, 
			Integer curPage, 
			String searchWord, 
			String memo,
			HttpSession session) throws Exception {
			
		Comment comment = boardService.getComment(commentNo);
		// login check and writer check
				User user = (User) session.getAttribute(WebContants.USER_KEY);
				if (user == null || !comment.getEmail().equals(user.getEmail())) {
					return "redirect:/users/login";
				}
		comment.setMemo(memo);
		boardService.updateComment(comment);
		
		searchWord = URLEncoder.encode(searchWord, "UTF-8");
		
		return "redirect:/bbs/view?articleNo=" + articleNo + 
			"&boardCd=" + boardCd + 
			"&curPage=" + curPage + 
			"&searchWord=" + searchWord;

	}
	
	@RequestMapping(value="/commentDel", method=RequestMethod.POST)
	public String commentDel(Integer commentNo, 
			Integer articleNo, 
			String boardCd, 
			Integer curPage, 
			String searchWord) throws Exception {
			
		boardService.deleteComment(commentNo);
		
		searchWord = URLEncoder.encode(searchWord,"UTF-8");
		
		return "redirect:/bbs/view?articleNo=" + articleNo + 
			"&boardCd=" + boardCd + 
			"&curPage=" + curPage + 
			"&searchWord=" + searchWord;

	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Integer articleNo, 
			String boardCd, 
			Model model) throws Exception {
		
		Article thisArticle = boardService.getArticle(articleNo);
		String boardNm = boardService.getBoardNm(boardCd);
		
		//수정페이지에서의 보일 게시글 정보
		model.addAttribute("thisArticle", thisArticle);
		model.addAttribute("boardNm", boardNm);
		
		return "bbs/modifyform";
	}
	//test해봐야됨
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(MultipartHttpServletRequest mpRequest) throws Exception {
		int articleNo = Integer.parseInt(mpRequest.getParameter("articleNo"));
		Article article = boardService.getArticle(articleNo);
		
		String boardCd = mpRequest.getParameter("boardCd");
		int curPage = Integer.parseInt(mpRequest.getParameter("curPage"));
		String searchWord = mpRequest.getParameter("searchWord");
		
		String title = mpRequest.getParameter("title");
		String content = mpRequest.getParameter("content");
		
		//게시글 수정
		article.setTitle(title);
		article.setContent(content);
		article.setBoardCd(boardCd);// 게시판 종류 변경
		boardService.update(article);

		//파일업로드
		Iterator<String> it = mpRequest.getFileNames();
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		while (it.hasNext()) {
			MultipartFile multiFile = mpRequest.getFile((String) it.next());
			if (multiFile.getSize() > 0) {
				String filename = multiFile.getOriginalFilename();
				multiFile.transferTo(new File(WebContants.BASE_PATH + filename));
				fileList.add(multiFile);
			}
		}
		
		//파일데이터 삽입
		int size = fileList.size();
		for (int i = 0; i < size; i++) {
			MultipartFile mpFile = fileList.get(i);
			AttachFile attachFile = new AttachFile();
			String filename = mpFile.getOriginalFilename();
			attachFile.setFilename(filename);
			attachFile.setFiletype(mpFile.getContentType());
			attachFile.setFilesize(mpFile.getSize());
			attachFile.setArticleNo(articleNo);
			boardService.insertAttachFile(attachFile);
		}
		searchWord = URLEncoder.encode(searchWord,"UTF-8");
		return "redirect:/bbs/view?articleNo=" + articleNo 
			+ "&boardCd=" + boardCd 
			+ "&curPage=" + curPage 
			+ "&searchWord=" + searchWord;

	}
	
	@RequestMapping(value="/attachFileDel", method=RequestMethod.POST)
	public String attachFileDel(Integer attachFileNo, 
			Integer articleNo, 
			String boardCd, 
			Integer curPage, 
			String searchWord) throws Exception {
		
		boardService.deleteFile(attachFileNo);
		
		searchWord = URLEncoder.encode(searchWord,"UTF-8");
		
		return "redirect:/bbs/view?articleNo=" + articleNo + 
			"&boardCd=" + boardCd + 
			"&curPage=" + curPage + 
			"&searchWord=" + searchWord;

	}
	//end
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(Integer articleNo, 
			String boardCd, 
			Integer curPage, 
			String searchWord) throws Exception {
		
		boardService.delete(articleNo);

		searchWord = URLEncoder.encode(searchWord, "UTF-8");
		
		return "redirect:/bbs/list?boardCd=" + boardCd + 
			"&curPage=" + curPage + 
			"&searchWord=" + searchWord;

	}
	
	
	@RequestMapping(value="/moveToWriteForm", method=RequestMethod.POST)
	public String moveToWriteForm(Article article) {
		
		return "bbs/writeform";
		
	}
	
	@RequestMapping(value="/moveToWriteFormTest", method=RequestMethod.POST)
	public String moveToWriteFormTest(Article article) {
		
		return "bbs/writeformTest";
		
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(Article article) {
		
		//article.setEmail("bond007@gmail.org");
		boardService.insert(article);
		return "redirect:/bbs/list?boardCd=" + article.getBoardCd();
	}
	
	@RequestMapping(value="/download", method=RequestMethod.POST)
	public String download(String filename, Model model) {
		model.addAttribute("filename", filename);
		return "inc/download";
	}
	
}