package jp.co.internous.pancake.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.pancake.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.pancake.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.pancake.model.session.LoginSession;

@Controller
@RequestMapping("/pancake/history")
public class PurchaseHistoryController {
	
	@Autowired
	private LoginSession loginSession;
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
		
	
	// purchase_history.htmlを表示する
	@RequestMapping("/")
	public String index(Model m) {
		// セッションが持ち回っているログイン情報の中からUserIdを取得する
		int userId = loginSession.getUserId();
		// findPurchaseHistoryメソッドでUserIdに紐づいた履歴を取得しhistoriesリストに格納
		List<PurchaseHistoryDto> histories = purchaseHistoryMapper.findByUserId(userId);
		// コントローラからhistoriesという名前でビューに値を渡す
		m.addAttribute("histories", histories);
		m.addAttribute("loginSession",loginSession);
		
		return "purchase_history";
	}
		
		
	// deleteメソッドでstatusの値を0に更新する
	@ResponseBody
	@RequestMapping("/delete")
	public boolean delete() {	
		// セッションが持ち回っているログイン情報の中からUserIdを取得する
		int userId = loginSession.getUserId();
		// deleteメソッドでUserIdに紐づいた履歴のステータスを1から0に更新する
		int result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
	
		return result > 0;
	
	}
}
