package jp.co.internous.pancake.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.pancake.model.domain.MstDestination;
import jp.co.internous.pancake.model.mapper.MstDestinationMapper;
import jp.co.internous.pancake.model.mapper.TblCartMapper;
import jp.co.internous.pancake.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.pancake.model.session.LoginSession;

@Controller
@RequestMapping("/pancake/settlement")
public class SettlementController {

	@Autowired
	private MstDestinationMapper destinationMapper;
	@Autowired
	private LoginSession loginSession;
	@Autowired
	private TblCartMapper cartMapper;
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	private Gson gson = new Gson();

	
	//初期表示
	@RequestMapping("/")
	public String index(Model m) {
		//セッションが持ち回っているログイン情報の中からUserIdを取得する
		int userId = loginSession.getUserId();
		
		//findMstDestinationメソッドでUserIdに紐づいた登録宛先を取得しdestinationsリストに格納
		List<MstDestination> destinations = destinationMapper.findByUserId(userId);
		//コントローラからdestinationsという名前でビューに値を渡す
		m.addAttribute("destinations", destinations);
		m.addAttribute("loginSession",loginSession);

		return "settlement";
	}
	
	//  決済ボタン押下　POSTリクエストを発行し(input type="radio"の値)@RequestBodyでそれを受け取る	
	@RequestMapping("/complete")
	@SuppressWarnings("unchecked")
	@ResponseBody
	public boolean complete(@RequestBody String destinationId) {
		//セッションが持ち回っているログイン情報の中からUserIdを取得する
		int userId = loginSession.getUserId(); 
		
		Map<String, String> map = gson.fromJson(destinationId, Map.class);
		String id = map.get("destinationId");
		

		// 引数としてparameterの値を送りpurchaseHistoryMapper.insertを動かす
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("destinationId", id);
		parameter.put("userId", userId);
		int insertCount = purchaseHistoryMapper.insert(parameter);
		
		//	int deleteCountを一旦初期化、履歴に登録したもの(購入したもの)があればカート情報をdeleteByUserIdで削除する
		int deleteCount = 0;
		if (insertCount > 0) {
			deleteCount = cartMapper.deleteByUserId(userId);
		}
		
		//削除したカート内のアイテムと履歴に登録したアイテムの数が正しいかどうかの真偽値(boolean)を返している
		return deleteCount == insertCount;
	}
}
	
	
	
