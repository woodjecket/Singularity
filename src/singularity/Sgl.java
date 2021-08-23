package singularity;

import arc.files.Fi;
import arc.files.ZipFi;
import arc.struct.Seq;
import arc.util.serialization.Jval;
import mindustry.gen.Building;
import singularity.world.atmosphere.Atmospheres;
import singularity.world.atmosphere.GasAreas;
import singularity.world.reaction.ReactionPoints;
import singularity.world.reaction.Reactions;

import static arc.Core.settings;

public class Sgl{
  /**此mod内部名称*/
  public static final String modName = "singularity";
  /**最大多方块结构尺寸限制*/
  public static final int maxStructSizeLimit = 50;
  /**空白实体数组*/
  public static final Seq<Building> empty = new Seq<>(0);
  
  /**本模组的文件位置*/
  public static final Fi modDirectory = settings.getDataDirectory().child("mods");
  /**本模组的文件位置*/
  public static final Fi modFile = getModFile(modName);
  /**模组内配置文件存放位置*/
  public static final Fi internalConfigDir = modFile.child("config");
  /**模组数据文件夹*/
  public static final Fi dataDirectory = modDirectory.child("data").child(modName);
  /**模组配置文件夹*/
  public static final Fi configDirectory = modDirectory.child("config").child(modName);
  /**模组的mod_config.ini配置文件*/
  public static final Fi configFile = configDirectory.child("mod_config.ini");
  
  //URIs
  public static final String qq = "https://qm.qq.com/cgi-bin/qm/qr?k=wLs-Tki9wGMJtJs2mWSc46fUusYk-oO1&noverify=0";
  public static final String telegram = "https://t.me/EB_wilson";
  public static final String facebook = "https://www.facebook.com/profile.php?id=100024490163405";
  public static final String qqGroup1 = "";
  public static final String qqGroup2 = "";
  public static final String telegramGroup = "";
  public static final String modDevelopGroup = "https://jq.qq.com/?_wv=1027&k=vjybgqDG";
  public static final String githubProject = "https://github.com/EB-wilson/Singularity";
  
  /**所有大气的全局存储对象，提供了关于大气层的一些参数与操作*/
  public static Atmospheres atmospheres;
  /**气体云的全局存储对象，提供了气体散逸成云的功能和关于气体云的集中操作*/
  public static GasAreas gasAreas;
  /**反应的全局存储对象，保存了所有的反应类型，并提供了匹配反应的方法*/
  public static Reactions reactions;
  /**所有反应点的全局存储对象，用于保存和统一操作反应点*/
  public static ReactionPoints reactionPoints;
  
  public static void init(){
    atmospheres = new Atmospheres();
    gasAreas = new GasAreas();
    reactions = new Reactions();
    reactionPoints = new ReactionPoints();
    
    atmospheres.init();
  }
  
  public static Fi getModFile(String modName){
    Fi[] modsFiles = modDirectory.list();
    Fi temp = null;
    
    for(Fi file : modsFiles){
      if(file.isDirectory()) continue;
      Fi zipped = new ZipFi(file);
      Fi modManifest = zipped.child("mod.hjson").exists()? zipped.child("mod.hjson"): zipped.child("mod.json");
      if(modManifest.exists()){
        String name = Jval.read(modManifest.readString()).get("name").toString();
        if(name.equals(modName)) temp = zipped;
      }
    }
    
    return temp;
  }
}
