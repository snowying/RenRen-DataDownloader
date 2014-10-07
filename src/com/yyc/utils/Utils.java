package com.yyc.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.yyc.controller.AlbumDownloader;
import com.yyc.controller.Downloader;
import com.yyc.controller.PhotoDownloader;
import com.yyc.controller.PostDownloader;
import com.yyc.model.Type;

public class Utils {
	static String TOKEN1 = "272253|6.3fb2bab1b813b90bab82ac19593dbe7e.2592000.1415199600-251529943";
	static String TOKEN2 = "272253|6.3fb2bab1b813b90bab82ac19593dbe7e.2592000.1415199600-251529943"; // 洪新华
	static String TOKEN3 = "272255|6.6b05c20e7d6e08f54f425eae08f1904a.2592000.1415199600-271380118"; // 叶弘
	static String TOKEN4 = "272256|6.aa8e71d505454ee418228085cf3f8e4e.2592000.1415199600-267569792"; // 叶其成
	static String USERID = "142782732";

	public static List<Type> download(Downloader d) throws JSONException {
		List<Type> results = new ArrayList<Type>();
		for (int pageNumber = 1; pageNumber < d.getPageNumberMax(); pageNumber++) {
			String url = d.getURL(TOKEN1, USERID, pageNumber);
			String result = "";
			BufferedReader in = null;
			try {
				URL realUrl = new URL(url);
				// 打开和URL之间的连接
				URLConnection connection = realUrl.openConnection();
				// 设置通用的请求属性
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection
						.setRequestProperty("user-agent",
								"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				// 建立实际的连接
				connection.connect();
				// 定义 BufferedReader输入流来读取URL的响应
				in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			results.addAll(d.parse(new ArrayList<Type>(), result));
		}
		return results;
	}

	public static void downloadPhotos() throws IOException, JSONException {
		File f = new File("C:\\Users\\Yicheng Ye\\Desktop\\photos.json");
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = null;
		while ((line = br.readLine()) != null) {
			JSONTokener token = new JSONTokener(line);
			JSONObject root = new JSONObject(token);
			if (root.has("images")) {
				JSONArray objs = root.getJSONArray("images");
				for (int i = 0; i < objs.length(); i++) {
					JSONObject obj = objs.getJSONObject(i);
					URL url = new URL(obj.getString("url"));
					File outFile = new File(
							"C:\\Users\\Yicheng Ye\\Desktop\\photos\\"
									+ obj.getString("id") + ".jpg");
					BufferedOutputStream bo = new BufferedOutputStream(
							new FileOutputStream(outFile));
					InputStream is = url.openStream();
					byte[] buff = new byte[1024];
					while (true) {
						int readed = is.read(buff);
						if (readed == -1) {
							break;
						}
						byte[] temp = new byte[readed];
						System.arraycopy(buff, 0, temp, 0, readed);
						bo.write(temp);
					}
					is.close();
					bo.close();
				}
			}
			br.close();
		}
	}

	public static void main(String[] args) throws JSONException, IOException {
		String[] albumIds = { "1051402333", "545190086", "620436952",
				"383221514", "860589664", "299460374", "850534255",
				"643003724", "637223588", "584258516", "578639499",
				"557531844", "525532985", "392741963", "458122854",
				"514510387", "473456097", "467629089", "435383780",
				"431376801", "428463460", "417669702", "392120324",
				"406748669", "395736599", "394064930", "391390976",
				"381245915", "354191698", "354189426", "331620403",
				"328927795", "328922896", "324748890", "319449730",
				"315837266", "306376165", "288509238", "266668519",
				"253425867", "245026799", "244584474", "226163483",
				"224336821", "214422837", "210203017", "205044145" };

		/*
		 * List<Type> photoResults = new ArrayList<Type>(); Downloader d = new
		 * PhotoDownloader("1051402333"); // System.out.println(download(d));
		 * for (int i = 0; i < albumIds.length; i++) {
		 * photoResults.addAll(download(d)); } System.out.println(photoResults);
		 */

		/*
		 * List<Type> postResults = new ArrayList<Type>(); Downloader dd = new
		 * PostDownloader(); postResults.addAll(download(dd)); //
		 * System.out.println(postResults);
		 */

		/*
		 * List<Type> albumResults = new ArrayList<Type>(); Downloader ddd = new
		 * AlbumDownloader(); albumResults.addAll(download(ddd));
		 * System.out.println(albumResults);
		 */
	}
}
