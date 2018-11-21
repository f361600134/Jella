package fatiny.myTest.actor;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import fatiny.myTest.actor.impl.IPlayerActor;

/**
 * 玩家上下文
 * 
 * @author huachp
 */
public class PlayerContext {
	
	/** 玩家ID */
	private long playerId;
	/** 玩家数据对象 */
	private Player player;
	/** 玩家Actor */
	private IPlayerActor playerActor;
	
	public PlayerContext() {
		
	}
	
	public PlayerContext(Player player) {
		this.player = player;
		this.playerId = player.getId();
	}
	
	public PlayerContext(long playerId) {
		this.playerId = playerId;
	}
	
	public long getId() {
		return playerId;
	}
	
	public String getPlayerName() {
		return player.getName();
	}
	
	public long getPlayerId() {
		return player.getId();
	}

	public Player getPlayer() {
		return player;
	}

//	public IPlayerActor getPlayerActor() {
//		if (playerActor == null) {
//			playerActor = getAndInitActor();
//		}
//		return playerActor;
//	}
	
	public void initActor(IPlayerActor playerActor) {
		this.playerActor = playerActor;
		this.playerActor.initContext(this);
	}
	

	public boolean checkResEnough(int resType, int count) {
//		switch (resType) {
//			case ResType.GOLD:  	return checkGolden(count);
//			case ResType.ENERGY: 	return checkEnergy(count);
//			case ResType.POWER: 	return checkPower(count); 
//			case ResType.HONOR:		return checkHonor(count);
//			case ResType.MERIT:		return checkMerit(count);
//			case ResType.POTENCE: 	return checkPotence(count);
//			case ResType.CONTRIBUTION: return checkContribution(count);
//			case ResType.PRESTIGE: return checkPrestige(count);
//			default: 				return false;
//		}
		return false;
	}
	
	
	private boolean addRes(int resType, int count, String source) {
		boolean added;
//		switch (resType) {
//			case ResType.GOLD:  
//				added = addGolden(count);
//				try {
//					PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("金币记录日志错误：{}", e);
//				}
//				return added;
//			case ResType.ENERGY: 	
//				added = addEnergy(count);
//				try {
//					PlayerLog.obtainEnergy(this, count, source);
//				} catch (Exception e) {
//					GameLog.error("能源记录日志错误：{}", e);
//				}
//				return added;
//			case ResType.POWER:
//				added = addPower(count);
//			    return added;
//			case ResType.HONOR:
//				added = addHonor(count);
//				getPlayerRecord().addHonor(count);
//			    try {
//				    PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("荣誉记录日志错误：{}", e);
//				}
//                return added;
//			case ResType.MERIT:
//				added = addMerit(count);
//			    getPlayerRecord().addMerit(count);
//			    try {
//				    PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("功勋记录日志错误：{}", e);
//				}
//                return added;
//			case ResType.PLAYER_EXP:	
//				int oldLevel = getLevel();
//				addExp(count);
//				int newLevel = getLevel();
//				try {
//					PlayerLog.obtainExp(this, oldLevel, newLevel, count, source);
//				} catch (Exception e) {
//					GameLog.error("添加经验记录日志错误：{}", e);
//				}
//				return true;
//            case ResType.POTENCE:
//            	added = addPotence(count);
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("添加潜能点记录日志错误：{}", e);
//				}
//                return added;
//            case ResType.CONTRIBUTION:
//            	added = addContribution(count);
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("个人贡献点记录日志错误：{}", e);
//				}
//                return added;
//            case ResType.PRESTIGE:
//            	added = addPrestige(count);
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("声望点记录日志错误：{}", e);
//				}
//                return added;
//            case ResType.VIPEXP:
//            	VipContext vipContext = VipManager.insance.getVipContext(this.getPlayerId());
//        		if(vipContext != null) {
//        			vipContext.addVipExp(this, count);
//        			vipContext.pushVipInfo(this);
//        		}
//            	try {
//                	PlayerLog.obtainNotRechargeRes(this, count, source, resType);
//				} catch (Exception e) {
//					GameLog.error("vip经验点记录日志错误：{}", e);
//				}
//                return count>0;
//            	;
//			default:
			    return false;
//		}

	}

	public boolean deductResource(int resType, int count, String source) {
		boolean deducted;
//		switch (resType) {
//			case ResType.GOLD:  	
//				deducted = deductGolden(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//				return deducted;
//			case ResType.ENERGY: 	
//				deducted = deductEnergy(count);
//				if (deducted) {
//					PlayerLog.consumeEnergy(this, count, source, 0, 1);
//					GameEventBus.instance().post(new PlayerConsumeEvent(this, resType, count));
//				}
//				return deducted;
//			case ResType.POWER: 	
//				deducted = deductPower(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//				return deducted;
//			case ResType.HONOR:			
//				deducted = deductHonor(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//				return deducted;
//            case ResType.POTENCE:   
//            	deducted = deductPotence(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//            	return deducted;
//            case ResType.CONTRIBUTION: 	
//            	deducted = deductContribution(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//            	return deducted;
//            case ResType.PRESTIGE: 	
//            	deducted = deductPrestige(count);
//				if (deducted) {
//					PlayerLog.consumeGolden(this, count, source, 0, 1, resType);
//				}
//            	return deducted;
//			default: 		
				return false;
//		}
	}
	
	
//	public void addExp(int exp) {
//		int preLevel = getLevel();
//		int upLevel = player.addExp(exp);
//		if (upLevel > preLevel) {
//			levelUp(preLevel, upLevel);
//		} else {
//			pushExpInfo();
//		}
//		player.update(); // 异步
//	}
	
	
	
//	private void pushLevelUp(int preLevel, int upLevel) {
//		PlayerResProto protocol = new PlayerResProto();
//		protocol.addAttr(CommonRes.LEVEL_ID, upLevel);
//		protocol.addAttr(CommonRes.EXP_ID, getExp());
//		protocol.addAttr(CommonRes.POWER_ID, player.getPower());
//		push(protocol);
//		
//		S2CLevelUp levelUpMsg = S2CLevelUp.newBuilder()
//				.setOldLevel(preLevel).setUpLevel(upLevel).build();
//		short cmd = ProtocolCode.PLAYER_S2C_LEVEL_UP;
//		push(SimpleProtocol.create(cmd, levelUpMsg));
//	}
	
//	public void responseSuc() {
//		S2CCommonReply.Builder builder = S2CCommonReply.newBuilder();
//		builder.setProtoCode(this.operatingCmd);
//		builder.setSuccess(1);
//		push(SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, builder.build()));
//	}
	
	
//	public void responseSuc(int attachment) {
//		S2CCommonReply.Builder builder = S2CCommonReply.newBuilder();
//		builder.setProtoCode(this.operatingCmd);
//		builder.setSuccess(1);
//		builder.setAttachment(attachment);
//		push(SimpleProtocol.create(ProtocolCode.COMMON_S2C_REPLY, builder.build()));
//	}
	
	
	

}
