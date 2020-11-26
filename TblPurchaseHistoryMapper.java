package jp.co.internous.pancake.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.pancake.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {

	// SQL文はTblPurchaseHistoryMapper.xmlに記載 
	int insert(Map<String, Object> parameter);
	
	List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
	
	// interfaceのlogicalDeleteByUserIdが呼び出されたら実行されるSQL文 削除ボタンが押された際にその時点の履歴のステータスを全て0にUPDATEする(以降表示されない)
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}")
	int logicalDeleteByUserId(@Param("userId") int userId);
}


 