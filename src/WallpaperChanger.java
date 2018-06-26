import java.util.*;
import java.io.File;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.UINT;
import com.sun.jna.win32.*;

public class WallpaperChanger 
{
   public static void main(String[] args) 
   {
      String pathOfDir = "C:\\Users\\Michal\\Desktop\\[MIKE]\\02 PHOTO ALBUMS\\WallpaperChangerLibrary\\";      
      File wallpapers =  new File(pathOfDir);
      String pathOfNewImage = pickNewImage(pathOfDir, wallpapers);
      
      SPI.INSTANCE.SystemParametersInfo(
          new UINT(SPI.SPI_SETDESKWALLPAPER), 
          new UINT(0), 
          pathOfNewImage, 
          new UINT(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
   }
   public static String pickNewImage(String dirPath, File file)
   {
	   String jpg = ".jpg";
	   File[] files = file.listFiles();
	   Random generator  = new Random();
	   int newImage = generator.nextInt(files.length);
	   
	   String thePath = files[newImage].getName();
	   dirPath += thePath;
	   if(dirPath.endsWith(jpg))
		   thePath = dirPath;
	   else
		   thePath = "C:\\Users\\Michal\\Desktop\\[MIKE]\\02 PHOTO ALBUMS\\WallpaperChangerLibrary\\v3bvU.jpg";
	   
	   return thePath; 
   }
   public interface SPI extends StdCallLibrary 
   {
      long SPI_SETDESKWALLPAPER = 20;
      long SPIF_UPDATEINIFILE = 0x01;
      long SPIF_SENDWININICHANGE = 0x02;

      SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
         {
            put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
            put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
         }
      });

      boolean SystemParametersInfo(
          UINT uiAction,
          UINT uiParam,
          String pvParam,
          UINT fWinIni
        );
  }
}