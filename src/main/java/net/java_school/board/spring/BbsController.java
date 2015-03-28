package net.java_school.board.spring;

import java.util.ArrayList;

import net.java_school.board.Article;
import net.java_school.board.AttachFile;
import net.java_school.board.Comment;
import net.java_school.board.BoardService;
import net.java_school.commons.PagingHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		
		return "bbs/list";
		
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
	
	@RequestMapping(value="/moveToWriteForm", method=RequestMethod.POST)
	public String moveToWriteForm(Article article) {
		
		return "bbs/writeform";
		
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(Article article) {
		article.setEmail("bond007@gmail.org");
		boardService.insert(article);
		return "redirect:/bbs/list?boardCd=" + article.getBoardCd();
	}
	
}