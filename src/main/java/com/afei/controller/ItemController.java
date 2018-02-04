package com.afei.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.afei.model.Item;
import com.afei.model.SessionUser;
import com.afei.service.IItemService;
import com.afei.util.Constants;

@Controller
@RequestMapping("/itemController")
public class ItemController {
	private Logger logger=LoggerFactory.getLogger(ItemController.class);
	
	private IItemService itemService;

	public IItemService getItemService() {
		return itemService;
	}

	@Autowired
	public void setItemService(IItemService itemService) {
		this.itemService = itemService;
	}

	/**
	 * 项目管理页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/showManageItems.action")
	public String showManageItems(Model model,HttpSession session) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		int userId = sessionUser.getUserId();
		List<Item> expenditureItems = itemService.loadExpenditureItems(userId); 
		List<Item> incomeItems = itemService.loadIncomeItems(userId);
		model.addAttribute("expenditureItems", expenditureItems);
		model.addAttribute("incomeItems", incomeItems);
		model.addAttribute("sessionUser", sessionUser);
		
		return "pages/manageItems";
	}

	/**
	 * 增加item
	 * @param itemName
	 * @param remark
	 */
	@RequestMapping("/addItem.action")
	public void addItem(HttpSession session,String itemName, String remark, String inOrEx) {
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		int userId = sessionUser.getUserId();
		itemService.addItem(userId, itemName, remark, inOrEx);
	}

	/**
	 * 修改item
	 * 
	 * @param itemId
	 * @param itemName
	 * @param remark
	 * @param inOrEx
	 */
	@RequestMapping("/changeItem.action")
	public void changeItem(int itemId, String itemName, String remark, String inOrEx) {
		itemService.changeItem(itemId, itemName, remark);
	}
	
	/**
	 * 删除item
	 * @param itemId
	 */
	@RequestMapping("/deleItem.action")
	public void deleItem(int itemId) {
		itemService.deleItem(itemId);
	}
	
	/**
	 * item的上移和下移
	 * @param session
	 * @param itemId
	 * @param inOrEx
	 * @param upAndDown
	 * @return
	 */
	@RequestMapping(value = "/upAndDownItem.action", produces = "application/json;charset=UTF-8")
	public @ResponseBody Map<String ,Object> upAndDownItem(HttpSession session, int itemId, String inOrEx, String upAndDown) {
		Map<String, Object> map = new HashMap<String, Object>();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constants.SESSION_USER_KEY);
		int userId = sessionUser.getUserId();
		String msg = itemService.upAndDownItem(userId, itemId, inOrEx, upAndDown);
		map.put("info", msg);
		return map;
	}

}
