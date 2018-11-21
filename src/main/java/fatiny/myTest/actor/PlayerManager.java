package fatiny.myTest.actor;


/**
 * 玩家数据管理
 * 
 * @author huachp
 */
//public class PlayerManager implements ILogoutListener {
//	
//	private static PlayerManager manager = new PlayerManager();
//	
//	public static PlayerManager instance() {
//		return manager;
//	}
//	
//	/** {playerId=playerContext} */
//	private ConcurrentMap<Long, PlayerContext> idPlayers = new ConcurrentHashMap<>();
//	/** {account=playerContext} */
//	private ConcurrentMap<String, PlayerContext> accountPlayers = new ConcurrentHashMap<>();
//	
//	/** {userId=true} 正在登录中 */
//	private ConcurrentMap<String, Object> loginingCache = CacheBuilder.newBuilder()
//			        .maximumSize(8000)
//			        .expireAfterWrite(1, TimeUnit.MINUTES)
//			        .build(
//			            new CacheLoader<String, Object>() {
//			                public Object load(String key) throws Exception {
//			                    return true;
//			                }
//			        }).asMap();
//	
//	/** {playerId=playerActor} */
//	private LoadingCache<Long, IPlayerActor> playerActors = CacheBuilder.newBuilder()        
//					.expireAfterAccess(30, TimeUnit.MINUTES)
//					.removalListener(new RemovalListener<Long, IPlayerActor>() {
//						@Override
//						public void onRemoval(RemovalNotification<Long, IPlayerActor> notification) {
//							clearOfflineCache(notification.getKey());
//						}
//					})
//					.build(new CacheLoader<Long, IPlayerActor>() {
//						@Override
//						public IPlayerActor load(Long key) throws Exception {
//							return AkkaContext.createTypedActor(IPlayerActor.class, PlayerActor.class);
//						} 
//					});
//	
//	
//	
//	public PlayerContext getPlayer(Long playerId) {
//		if (!IdHelper.isValidId(playerId)) { // 不是有效的玩家ID, 返回null
//			return null;
//		}
//		PlayerContext playerCtx = idPlayers.get(playerId);
//		if (playerCtx == null) {
//			playerCtx = getPlayerById(playerId);
//			PlayerContext old = idPlayers.putIfAbsent(playerId, playerCtx);
//			if (old != null) {
//				playerCtx = old;
//			} else {
//				accountPlayers.put(playerCtx.getAccount(), playerCtx); // 加入账号缓存
//			}
//			playerCtx.initActor(getActor(playerId));
//		}
//		return playerCtx;
//	}
//	
//	
//	public PlayerContext getPlayer(String account,Object...o) {
//		Player player = null;
//		PlayerContext playerCtx = null;
//		playerCtx = accountPlayers.get(account);
//		if (o.clone().length == 0 && playerCtx != null) {
//			return playerCtx;
//		}
//		if(o.clone().length > 0)
//		{
//			player =  getPlayerFromDb(account,(int)o.clone()[0]);
//		}else
//		{
//			player = getPlayerFromDb(account);
//		}
//		if (player != null) {
//			playerCtx = new PlayerContext(player);
//		}else
//		{
//			return null;
//		}
//		return playerCtx;
//	}
//	
//	
//	private PlayerContext getPlayerById(long playerId) {
//		GameContext gameCtx = GameContext.instance();
//		List<Player> data = gameCtx.gameData().getData(Player.class, playerId);
//		if (data.isEmpty()) {
//			return null;
//		}
//		Player player = data.get(0);
//		PlayerContext playerCtx = new PlayerContext(player);
//		return playerCtx;
//	}
//	
//	
//	public PlayerContext createPlayer(long playerId, String account, String name, int sex) {
//		PlayerContext playerCtx = new PlayerContext();
//		Player player = playerCtx.create(playerId, account, name);
//		player.setSex(sex); // 设置性别
//		
//		player.save();
//		accountPlayers.put(account, playerCtx);	
//		idPlayers.put(playerId, playerCtx);
//		return playerCtx;
//	}
//	
//	
//	private Player getPlayerFromDb(String account) {
//		GameContext gameCtx = GameContext.instance();
//		String[] props = { Player.PROP_ACCOUNT };
//		Object[] values = { account };
//		List<Player> data = gameCtx.gameData().getByProps(-1, Player.class, props, values);
//		return data.isEmpty() ? null : data.get(0);
//	}
//	
//	private Player getPlayerFromDb(String account,int gameId) {
//		GameContext gameCtx = GameContext.instance();
//		String[] props = { Player.PROP_ACCOUNT };
//		Object[] values = { account };
//		List<Player> data = gameCtx.gameData().getByProps(gameId, Player.class, props, values);
//		return data.isEmpty() ? null : data.get(0);
//	}
//	
//	
//	public boolean validatePlayerName(String name) {
//		GameContext gameCtx = GameContext.instance();
//		String[] props = { Player.PROP_NAME };
//		Object[] values = { name };
//		List<Player> data = gameCtx.gameData().getByProps(-1, Player.class, props, values);
//		return data.isEmpty();
//	}
//	
//	
//	public IPlayerActor getActor(long playerId) {
//		try {
//			return playerActors.get(playerId);
//		} catch (ExecutionException e) {
//			GameLog.error("获取PlayerActor出错", e);
//			return null;
//		}
//	}
//	
//	
//	public void removeActor(long playerId) {
//		playerActors.invalidate(playerId);
//	}
//	
//	
//	public void cache(PlayerContext playerCtx) {
//		Player player = playerCtx.getPlayer();
//		accountPlayers.put(player.getAccount(), playerCtx);	
//		idPlayers.put(player.getPlayerId(), playerCtx);
//		removeActor(playerCtx.getPlayerId()); // 清理缓存中的Actor
//	}
//	
//	
//	public void removePlayer(Long playerId) {
//		PlayerContext playerCtx = idPlayers.remove(playerId);
//		if (playerCtx != null) {
//			Player player = playerCtx.getPlayer();
//			accountPlayers.remove(player.getAccount());
//		}
//	}
//	
//	
//	public void remove(PlayerContext context) {
//		idPlayers.remove(context.getPlayerId());
//		accountPlayers.remove(context.getAccount());
//	}
//	
//	
//	public boolean isLogining(String account, int gameId) {
//		Object flag = loginingCache.get(account);
//		if (flag == null) {
//			Object old = loginingCache.putIfAbsent(account, gameId);
//			return old == null ? false : true;
//		} else {
//			return true;
//		}
//	}
//	
//	
//	public Object endLogin(String account) {
//		return loginingCache.remove(account);
//	}
//	
//	
//	public PlayerRecord getPlayerRecord(Long playerId) {
//		PlayerRecord record = playerRecords.get(playerId);
//		if (record != null) {
//			return record;
//		}
//		GameContext gameCtx = GameContext.instance();
//		List<PlayerRecord> data = gameCtx.gameData().getData(PlayerRecord.class, playerId);
//		if (data.isEmpty()) {
//			record = new PlayerRecord(playerId);
//			record.saveAsync();
//		} else {
//			record = data.get(0);
//		}
//		playerRecords.put(playerId, record);
//		return record;
//	}
//	
//	
//	public void cacheRecord(PlayerRecord record) {
//		playerRecords.put(record.getPlayerId(), record);
//	}
//	
//	
//	public boolean isExistRecord(Long playerId) {
//		return playerRecords.containsKey(playerId);
//	}
//	
//	
//	public List<PlayerContext> onlinePlayers() {
//		return new ArrayList<>(idPlayers.values());
//	}
//	
//	
//	public int onlineCount() {
//		return idPlayers.size();
//	}
//	
//	
//	public void kickAllPlayers() {
//		for (PlayerContext playerCtx : idPlayers.values()) {
//			CommonInt32 commInt = CommonInt32.newBuilder().setParameter(1).build();
//			short code = ProtocolCode.PLAYER_S2C_KICK_PLAYER;
//			SimpleProtocol proto = SimpleProtocol.create(code, commInt);
//			playerCtx.push(proto);
//			if (playerCtx.isOnline()) {
//				playerCtx.getSession().disConnect();
//			}
//		}
//	}
//	
//	/**
//	 * 踢玩家下线
//	 */
//	public void kickPlayer(PlayerContext player)
//	{
//		CommonInt32 commInt = CommonInt32.newBuilder().setParameter(1).build();
//		short code = ProtocolCode.PLAYER_S2C_KICK_PLAYER;
//		SimpleProtocol proto = SimpleProtocol.create(code, commInt);
//		player.push(proto);
//		if (player.isOnline()) {
//			player.getSession().disConnect();
//		}
//	}
//	
//	
//	public boolean isPlayerOnline(Long playerId) {
//		PlayerContext playerCtx = idPlayers.get(playerId);
//		return playerCtx != null && playerCtx.isOnline();
//	}
//	
//	
//	public PlayerContext objContext(Long playerId) {
//		return idPlayers.get(playerId);
//	}
//	
//	
//	@Override
//	public void logout(PlayerContext playerContext) {
//		remove(playerContext);
//	}
//	
//	
//	private void clearOfflineCache(Long playerId) {
//		PlayerContext ctx = idPlayers.get(playerId);
//		if (ctx != null && ctx.getSession() == null) {
//			LogoutManager.cleanUpCache(ctx);
//			ctx.destroy();
//		}
//	}
	
	
//}
