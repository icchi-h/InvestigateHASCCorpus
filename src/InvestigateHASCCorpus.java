/*
* Title：InvestigateHASCCorpus
* 説明 ：HASC Corpusに含まえる各ファイル数や人数などを調査するプログラム
* @date Created on: 2016/05/19
* @author Author: Haruyuki Ichino
* @version 1.0
*
*/



import java.io.*;

public class InvestigateHASCCorpus {

    // データの場所指定
    final public static String dataPath = "./data/";
    // ファイル出力のフラグ
    final public static boolean outputFileFlag = false;
    // 出力ファイル
    final public static String outputFile = "./result.csv";

    // ファイルライター
    static PrintWriter pw = null;


    public static void main(String[] args) {

        // ファイル出力の準備
        if (outputFileFlag){
            try{
                File file = new File(outputFile);
                pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            } catch (IOException ex){
                System.out.println(ex);
            }
        }


        // 通常のファイル(隠しファイルでない)のみを取り出すフィルタの作成
        FilenameFilter normalFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (file.isHidden() == false){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // accファイルのみを取り出すフィルタ
        FilenameFilter accFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*acc.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // gyroファイルのみを取り出すフィルタ
        FilenameFilter gyroFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*gyro.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // magファイルのみを取り出すフィルタ
        FilenameFilter magFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*mag.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // locファイルのみを取り出すフィルタ
        FilenameFilter locFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*loc.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // pressファイルのみを取り出すフィルタ
        FilenameFilter pressFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*press.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // proxiファイルのみを取り出すフィルタ
        FilenameFilter proxiFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*proxi.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // lightファイルのみを取り出すフィルタ
        FilenameFilter lightFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*light.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // wifiファイルのみを取り出すフィルタ
        FilenameFilter wifiFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*wifi.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // metaファイルのみを取り出すフィルタ
        FilenameFilter metaFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*meta.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };
        // labelファイルのみを取り出すフィルタ
        FilenameFilter labelFileFilter = new FilenameFilter() {
            public boolean accept(File file, String name) {
                if (name.matches(".*label.*")){
                    return true;
                } else {
                    return false;
                }
            }
        };


        File data_dir = new File(dataPath);

        // ユーザカウンター
        int totalUserCount = 0;
        // ファイルカウンター
        int totalAccCount = 0;
        int totalGyroCount = 0;
        int totalMagCount = 0;
        int totalLocCount = 0;
        int totalPressCount = 0;
        int totalProxiCount = 0;
        int totalLightCount = 0;
        int totalWifiCount = 0;
        int totalLabelCount = 0;
        int totalMetaCount = 0;
        int activityAccCount = 0;
        int activityGyroCount = 0;
        int activityMagCount = 0;
        int activityLocCount = 0;
        int activityPressCount = 0;
        int activityProxiCount = 0;
        int activityLightCount = 0;
        int activityWifiCount = 0;
        int activityLabelCount = 0;
        int activityMetaCount = 0;
        int activityUserCount = 0;
        // 性別カウンター
        float male = 0;
        float totalMale = 0;
        float female = 0;
        float totalFemale = 0;
        float unknown = 0;
        float totalUnknown = 0;


        // data内のファイルを取得
        File[] activityDirs = data_dir.listFiles(normalFileFilter);
        System.out.println("Activity count = " + activityDirs.length);
        System.out.println();

        // 各行動ディレクトリにアクセス
        for (File activityDir : activityDirs){

            // 各ファイルならスキップ
            if(activityDir.isHidden()) continue;

            System.out.println("===================================================");
            System.out.println(activityDir);
            System.out.println("===================================================");

            // ファイルカウンターの初期化
            activityAccCount = 0;
            activityGyroCount = 0;
            activityMagCount = 0;
            activityLocCount = 0;
            activityPressCount = 0;
            activityProxiCount = 0;
            activityLightCount = 0;
            activityWifiCount = 0;
            activityLabelCount = 0;
            activityMetaCount = 0;
            activityUserCount = 0;
            // 性別カウンターの初期化
            male = 0;
            female = 0;
            unknown = 0;

            // 行動ディレクトリ内のpersonディレクトリを取得
            File[] personDirs = activityDir.listFiles(normalFileFilter);
            activityUserCount = personDirs.length;


            // 各personディレクトリにアクセス
            for(File personDir : personDirs){

                // 各ファイルならスキップ
                if(personDir.isHidden()) continue;

//                System.out.println("======================================");
//                System.out.println(person_dir.getName());
//                System.out.println("======================================");

                // personディレクトリ内の各ファイルを取得
                File[] accFiles = personDir.listFiles(accFileFilter);
                File[] gyroFiles = personDir.listFiles(gyroFileFilter);
                File[] magFiles = personDir.listFiles(magFileFilter);
                File[] locFiles = personDir.listFiles(locFileFilter);
                File[] pressFiles = personDir.listFiles(pressFileFilter);
                File[] proxiFiles = personDir.listFiles(proxiFileFilter);
                File[] lightFiles = personDir.listFiles(lightFileFilter);
                File[] wifiFiles = personDir.listFiles(wifiFileFilter);
                File[] metaFiles = personDir.listFiles(metaFileFilter);
                File[] labelFiles = personDir.listFiles(labelFileFilter);

                if (accFiles.length != metaFiles.length){
                    System.out.print("加速度とmetaの数あってないで!: ");
                    System.out.println(personDir.getName());
                }

                // カウンターの更新
                activityAccCount += accFiles.length;
                activityGyroCount += gyroFiles.length;
                activityMagCount += magFiles.length;
                activityLocCount = locFiles.length;
                activityPressCount = pressFiles.length;
                activityProxiCount = proxiFiles.length;
                activityLightCount = lightFiles.length;
                activityWifiCount = wifiFiles.length;
                activityMetaCount += metaFiles.length;
                activityLabelCount += labelFiles.length;

                // 各加速度ファイルにアクセス
//                for (File accFile : accFiles){
//                    getFileTime(accFile);
//                }

                // 各metaファイルにアクセス
                for(File metaFile : metaFiles) {

                    String meta_file_name = metaFile.getName();
//                    System.out.println(meta_file_name);

                    // 名前からID部分の取り出し
//                    int idx_hascID = meta_file_name.indexOf(".");
//                    String file_id = meta_file_name.substring(0, idx_hascID);

                    // 性別の取得
                    String gender = getGender(metaFile);
                    if (gender.equals("male")) male++;
                    else if (gender.equals("female")) female++;
                    else{
//                        System.out.println(gender);
                        unknown++;
                    }

                    // 年齢の取得
//                    String ages = getMetaElement(metaFile, "Generation");
//                    String age = ages.split(";")[0];
//                    System.out.println(age);
                    // HASC Corpusの年齢はあまりパターンが多くない

                    // 身長・体重の取得
                    float HeightWeght[] = getHeightWeight(metaFile);
                    String data = String.format("%s,%.1f,%.1f", gender, HeightWeght[0], HeightWeght[1]);
                    if (outputFileFlag) pw.println(data);

                    // 各metaファイルの処理終了
                }
                // 各personディレクトリの処理終了
            }

            // 各行動に含まれるファイル数の表示
            System.out.println("person count = " + activityUserCount);
            float activityFemaleUser = activityUserCount*(female/(male+female+unknown));
            float activityUnknownUser = activityUserCount*(unknown/(male+female+unknown));
            int activityMaleUser = activityUserCount - (int)activityFemaleUser - (int)activityUnknownUser;
            System.out.printf("\t(male: %d, female: %d, unknown: %d)\n", activityMaleUser, (int)activityFemaleUser, (int)activityUnknownUser);
            System.out.println("acc count = " + activityAccCount);
            System.out.println("gyro count = " + activityGyroCount);
            System.out.println("mag count = " + activityMagCount);
            System.out.println("loc count = " + activityLocCount);
            System.out.println("press count = " + activityPressCount);
            System.out.println("proxi count = " + activityProxiCount);
            System.out.println("light count = " + activityLightCount);
            System.out.println("wifi count = " + activityWifiCount);
            System.out.println("meta count = " + activityMetaCount);
            System.out.printf("\t(male: %d, female: %d, unknown: %d)\n", (int)male, (int)female, (int)unknown);
            System.out.println("label count = " + activityLabelCount);
            System.out.println();

            // ファイルカウンターの更新
            totalUserCount += activityUserCount;
            totalAccCount += activityAccCount;
            totalGyroCount += activityGyroCount;
            totalMagCount += activityMagCount;
            totalLocCount += activityLocCount;
            totalPressCount += activityPressCount;
            totalProxiCount += activityProxiCount;
            totalLightCount += activityLightCount;
            totalWifiCount += activityWifiCount;
            totalMetaCount += activityMetaCount;
            totalLabelCount += activityLabelCount;
            // 性別カウンターの更新
            totalMale += male;
            totalFemale += female;
            totalUnknown += unknown;
        }

        // 全体のファイル数の表示
        System.out.println("===================================================");
        System.out.println("Summary");
        System.out.println("===================================================");
        System.out.println("total user count = " + totalUserCount);
        float totalFemaleUser = totalUserCount*(totalFemale/(totalMale+totalFemale+totalUnknown));
        float totalUnknownUser = totalUserCount*(totalUnknown/(totalMale+totalFemale+totalUnknown));
        int totalMaleUser = totalUserCount - (int)totalFemaleUser - (int)totalUnknownUser;
        System.out.printf("\t(male: %d, female: %d, unknown: %d)\n", totalMaleUser, (int)totalFemaleUser, (int)totalUnknownUser);
        System.out.println("total acc count = " + totalAccCount);
        System.out.println("total gyro count = " + totalGyroCount);
        System.out.println("total mag count = " + totalMagCount);
        System.out.println("total loc count = " + totalLocCount);
        System.out.println("total press count = " + totalPressCount);
        System.out.println("total proxi count = " + totalProxiCount);
        System.out.println("total light count = " + totalLightCount);
        System.out.println("total wifi count = " + totalWifiCount);
        System.out.println("total meta count = " + totalMetaCount);
        System.out.printf("\t(male: %d, female: %d, unknown: %d)\n", (int)totalMale, (int)totalFemale, (int)totalUnknown);
        System.out.println("total label count = " + totalLabelCount);

        // 出力ファイルの終了処理
        if (outputFileFlag) pw.close();

    }

    static String getGender(File file){

        // 性別タグ
        final String genderTAG = "Gender";

        try {
            //ファイルを読み込む
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //読み込んだファイルを１行ずつ処理する
            String line_str;
            for(int i=0; (line_str = br.readLine()) != null; i++){

                // Genderの処理
                if (line_str.indexOf(genderTAG) != -1) {


                    // TerminalMountから内容の取り出し
                    int idx_gender = line_str.indexOf(":") + 1;
                    String gender = line_str.substring(idx_gender, line_str.length());
                    gender = gender.trim(); // 空白の削除

//                    System.out.println(gender);
                    return gender;
                }
            }

            //終了処理
            br.close();

        } catch (IOException ex) {
            //例外発生時処理
            ex.printStackTrace();
        }

        //
        return "unknown";
    }

    static float[] getHeightWeight(File file){

        // タグ
        final String heightTAG = "Height";
        final String weightTAG = "Weight";

        // 返却する配列
        float ans[] = {-1, -1};

        // 身長と体重の取得フラグ
        boolean getHeight_flag = false;
        boolean getWeight_flag = false;

        try {
            //ファイルを読み込む
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //読み込んだファイルを１行ずつ処理する
            String line_str;
            for(int i=0; (line_str = br.readLine()) != null; i++){

                // Heightの処理
                if (line_str.indexOf(heightTAG) != -1) {


                    // Heightから内容の取り出し
                    int idx_height = line_str.indexOf(":") + 1;
                    String height = line_str.substring(idx_height, line_str.length());
                    height = height.trim(); // 空白の削除

//                    System.out.println(height);
                    try {
                        ans[0] = Float.parseFloat(height);
                        getHeight_flag = true;
                    } catch (NumberFormatException ex){
                    }
                }
                // Weightの処理
                else if (line_str.indexOf(weightTAG) != -1) {


                    // Weightから内容の取り出し
                    int idx_weight = line_str.indexOf(":") + 1;
                    String weight = line_str.substring(idx_weight, line_str.length());
                    weight = weight.trim(); // 空白の削除

//                    System.out.println(weight);
                    try {
                        ans[1] = Float.parseFloat(weight);
                        getWeight_flag = true;
                    } catch(NumberFormatException ex){
                    }
                }

                if (getHeight_flag == true && getWeight_flag == true) return ans;
            }

            //終了処理
            br.close();

        } catch (IOException ex) {
            //例外発生時処理
            ex.printStackTrace();
        }

        //
        return ans;
    }

    static String getMetaElement(File file, String TAG){

        try {
            //ファイルを読み込む
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            //読み込んだファイルを１行ずつ処理する
            String line_str;
            for(int i=0; (line_str = br.readLine()) != null; i++){

                // Genderの処理
                if (line_str.indexOf(TAG) != -1) {


                    // TerminalMountから内容の取り出し
                    int idx_target = line_str.indexOf(":") + 1;
                    String target = line_str.substring(idx_target, line_str.length());
                    target = target.trim(); // 空白の削除

                    return target;
                }
            }

            //終了処理
            br.close();

        } catch (IOException ex) {
            //例外発生時処理
            ex.printStackTrace();
        }

        //
        return "";
    }


//    static private void getFileTime(File file){
//        try {
//
//            FileInputStream input = new FileInputStream(file);
//            InputStreamReader stream = new InputStreamReader(input);
//            BufferedReader buffer = new BufferedReader(stream);
//
//            String line;
//
//            while ((line = buffer.readLine()) != null) {
//
//                byte[] b = line.getBytes();
//                line = new String(b, "UTF-8");
//                String[] columns = line.split(",",-1);
//
//                for (int j = 0; j < columns.length; j++) {
//                    System.out.print(columns[j] + "\t");
//                }
//
//                System.out.println("");
//
//            }
//
//            input.close();
//            stream.close();
//            buffer.close();
//
//        } catch (UnsupportedEncodingException | FileNotFoundException e) {
//            e.printStackTrace();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
//    }
}

