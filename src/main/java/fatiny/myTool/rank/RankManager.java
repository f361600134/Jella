package fatiny.myTool.rank;


/**
 * @author Jeremy Feng
 *
 */
public class RankManager{
	
	public static RankManager rankManager = new RankManager();
	
	private RankContext rankCtx;
	
	public static RankManager instance(){
        return rankManager;
    }

	public RankContext getRankCtx(){
		if (rankCtx == null) {
			rankCtx = new RankContext();
		}
		return rankCtx;
	}

}
