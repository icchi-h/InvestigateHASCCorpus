# HASC Corpus調査プログラム
HASC Corpusに含まれる各ファイル数や人数などを調査するプログラム
* 性別も計算できるように改良(2016/05/23)

## 使い方
#### IntelliJ IDEAから使う場合
1. このプロジェクトを読み込む
2. dataディレクトリにHASCコーパスのデータを入れる.
3. 実行
4. result.txtが出力される.

#### コマンドラインから使う場合
1. javaファイルを適当なティレクトリにコピー
2. javaファイルがある階層にcdで移動
3. javaファイルと同ディレクトリにdataディレクトリを用意.
4. dataディレクトリにHASCコーパスのデータを入れる.
5. 以下のコマンドを実行
6. result.txtが出力される.

##### コマンド
```
javac InvestigateHASCCorpus.java
java InvestigateHASCCorpus.java
```


### データのディレクトリ構成
```
.  
data  
├── 1_stay  
│  ├── person0001  
│  │   ├── HASCXXXXXX-acc.csv  
│  │   ├── HASCXXXXXX-gyro.csv  
│  │   ├── HASCXXXXXX-mag.csv  
│  │   └── ...  
│  ├── person0002  
│  └── ...  
├── 2_walk  
│　├── person0001  
│　│   ├── HASCXXXXXX-acc.csv  
│　│   ├── HASCXXXXXX-gyro.csv  
│　│   ├── HASCXXXXXX-mag.csv  
│　│   └── ...  
│　├── person0002  
│　└── ...  
└── ...
```


### 　
Developed by icchi  
2016/05/19
