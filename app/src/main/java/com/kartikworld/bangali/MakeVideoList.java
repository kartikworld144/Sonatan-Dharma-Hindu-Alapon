
package com.kartikworld.bangali;

import java.util.ArrayList;
import java.util.HashMap;

public class MakeVideoList {

	public static ArrayList< ArrayList<HashMap<String,String>> > rootArrayList;
	public static ArrayList< HashMap<String, String> > catArrayList;
	public static ArrayList< HashMap<String, String> > videoArrayList;
	public static HashMap<String, String> hashMap;


	//========================================================================
	// ===============================addVideoItem automation by Kartik

	public static void addVideoItem(String video_id, String title, String description){
		hashMap = new HashMap<>();
		hashMap.put("video_id", video_id);
		hashMap.put("vdo_title", title);
		hashMap.put("vdo_description", description);
		videoArrayList.add(hashMap);
	}

	//========================================================================
	//===============================createPlayListForVideo automation by Kartik

	public static void createPlayListForVideo(String category_name, Integer drawable){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}

	//========================================================================
	//===============================createCategoryForWebsite automation by Kartik

	public static void createCategoryForWebsite(String category_name, Integer drawable, String url){
		rootArrayList.add(videoArrayList);
		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		hashMap.put("url", url);
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}


	//========================================================================
	//===============================createCategoryForPDF automation by Kartik

	public static void createCategoryForPDF(String category_name, Integer drawable, String pdfAssetName){

		rootArrayList.add(videoArrayList);

		hashMap = new HashMap<>();
		hashMap.put("category_name", category_name);
		hashMap.put("img", String.valueOf(drawable));
		hashMap.put("pdfAssetName", pdfAssetName);
		catArrayList.add(hashMap);
		videoArrayList = new ArrayList<>();
	}

	//==========================================================
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>
	//---------------------------------------------------->>>>>>

	public static void createMyAlbums(){

		rootArrayList = new ArrayList();
		catArrayList = new ArrayList<>();
		videoArrayList = new ArrayList<>();


		//========================Video Item Category

		addVideoItem("Qj5DRtOmGhE", "সমগ্র ভাগবত গীতার সারমর্ম গীতামৃত কোর্স (পর্ব-1) ভগবত গীতার মাহাত্ম্য Bhagavad Gita class", "1.শ্রীমদ্ভগবদগীতা যথাযথ কেন পড়বো? 2.ভগবতগীতার ভূমিকা ও মুখবন্ধ কেন এত গুরুত্বপূর্ণ? 3.জাগতিক পান্ডিত\u200D্য দিয়ে কি ভগবত গীতা বোঝা যায়?");
		addVideoItem("a9oSkESIUkE", "Gita is shruti or smriti? | গীতামৃত কোর্স (পর্ব-2) Bhagavad Gita class Sumitra krishna das", "1.ভগবত গীতার বিভাজন\n" +
				"2.গীতা কি স্মৃতিশাস্ত্র নাকি শ্রুতিশাস্ত্র?\n" +
				"3.গীতায় সম্মন্ধ-অভিধেয়-প্রয়োজন তত্ত্ব");
		addVideoItem("dN6DYAZnz8s", "ইসকনের কি কোন পরম্পরা নেই ? | গীতামৃত কোর্স (পর্ব-3 ) Bhagavad Gita class", "1.মন্ত্র পরম্পরা ও শ্রবন পরম্পরা কি?\n" +
				"2. কোন পরম্পরার মাধ\u200D্যমে পরম্পরা ধারা শুরু হয়েছিল?\n" +
				"3.ইস্কনের যথার্থ পরম্পরা চার্ট  ");
		addVideoItem("_NG5422ecWY", "ভক্তি পথের বাধা কিভাবে কাটিয়ে উঠবেন? | গীতামৃত কোর্স (পর্ব-4 ) Bhagavad Gita class", "1.অর্জুনের বিষাদের ৫ টি কারণ ও শ্রীকৃষ্ণের ব\u200D্যাখ\u200D্যা\n" +
				"2.ভগবানের নিত\u200D্য পার্ষদ অর্জুন কি সত\u200D্যিই মোহগ্রস্ত হয়েছিলেন?\n" +
				"3.মোহগ্রস্ত ভক্ত ও মোহগ্রস্ত অভক্তের পার্থক\u200D্য কি?");
		addVideoItem("9u-97lvwSTQ", "পিন্ডদান ও তর্পনের বৈজ্ঞানিক ব্যাখ্যা | গীতামৃত কোর্স (পর্ব-5 ) Bhagavad Gita class\n", "1.বেদের উল্লিখিত ৬ প্রকার শত্রুতার মধ\u200D্যে কৌরবরা কি কি শত্রুতা করেছিলেন?\n" +
				"2.বেদে এই ছয় প্রকার আততায়ীদের শাস্তির জন\u200D্য কি বিধান দেওয়া হয়েছে?\n" +
				"3.কুলধর্ম ও সনাতন ধর্ম কি?");
		addVideoItem("z6LA0gc4xOY", "কখন গুরুদেবকে ত্যাগ করা উচিত? | গীতামৃত কোর্স (পর্ব-6 ) Bhagavad Gita class\n", "১. কিভাবে করুণা প্রদর্শণ করতে হয়?\n" +
				"২. গীতায় ভগবান শ্রীকৃষ্ণের বলা প্রথম পূর্ণশ্লোক কোনটি?\n" +
				"৩. \"শ্রীভগবানুবাচ\" - এই শব্দটি ব\u200D্যবহারের মাধ\u200D্যমে শ্রীল ব\u200D্যাসদেব কি বোঝাতে চেয়েছেন?\n" +
				"৪. আর্য ও অনার্য কারা?");
		addVideoItem("PcnmsEr97pE", "সৎ গুরু কিভাবে চিনতে হয়? || গীতামৃত কোর্স (পর্ব-7 ) Bhagavad Gita class\n", "১. কৃপণ ও ব\\u200D্রাম্মণ কারা?\\n\" +\n" +
				"\t\t\t\t\"২. অর্জুন নিজেকে কৃপণ বলেছেন কেন?\\n\" +\n" +
				"\t\t\t\t\"৩. মোহমুক্তির জন\\u200D্য অর্জুনের শ্রীকৃষ্ণের চরণে আত্মসমর্পন ও শ্রীকৃষ্ণকে গুরুরূপে গ্রহন।");
		addVideoItem("DUR-d2ITkjc", "দুঃখ কি করে সহ্য করবো? || গীতামৃত কোর্স (পর্ব-8 ) Bhagavad Gita class\n", "১. আধ\u200D্যাত্মিক উপলব্ধির জন\u200D্য কোন বিষয়ে জানা প্রয়োজন?\n" +
				"২.আত্মাকে উপলব্ধি করতে গেলে কি করতে হবে?\n" +
				"৩. মৃত\u200D্যুশোক দূর করার উপায়।");
		addVideoItem("u0XNc-IBGyE", "শরীরের কষ্ট কখন আমাদের বিচলিত করবে না ? || গীতামৃত কোর্স (পর্ব-9 ) Bhagavad Gita class\n", "১.আত্মার বৈশিষ্ঠ\u200D্য।\n" +
				"২.আত্মোপলব্ধির দ্বারা ভগবানকে উপলব্ধি।\n" +
				"৩. 'নায়ং হন্তি ন হন\u200D্যতে' - এই কথার মাধ\u200D্যমে শ্রীকৃষ্ণ কি আমাদের হিংসা করতে উদ্বুদ্ধ করছেন?");
		addVideoItem("Zf3iov8ubfw", "বেদের মধ্যেও নাস্তিক দর্শন || গীতামৃত কোর্স (পর্ব-10 ) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. কৃপণ ও ব\u200D্রাম্মণ কারা?\n" +
				"২. অর্জুন নিজেকে কৃপণ বলেছেন কেন?\n" +
				"৩. মোহমুক্তির জন\u200D্য অর্জুনের শ্রীকৃষ্ণের চরণে আত্মসমর্পন ও শ্রীকৃষ্ণকে গুরুরূপে গ্রহন।");
		addVideoItem("u5-w9yJ37Aw", "যজ্ঞে পশুবলি দিলে কি হয় ? || গীতামৃত কোর্স (পর্ব-11 ) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. আত্মার আশ্চর্যবৎ এর describtion।\n" +
				"২. ভগবান অর্জুনের করুণার যুক্তি কিভাবে খন্ডন করলেন?\n" +
				"৩. স্বধর্ম কত প্রকার ও কি কি?");
		addVideoItem("VE0Jl_Cpk3s", "কর্ম করেও কিভাবে কর্মফল থেকে মুক্ত হব ? || গীতামৃত কোর্স (পর্ব-12 ) Bhagavad Gita class\n", "১. কোন শ্লোকগুলিতে ভগবান অর্জুনের উপভোগের যুক্তি খন্ডন করেছেন?\n" +
				"২. ভগবান শ্রীকৃষ্ণ অর্জুনকে কর্মকান্ডীয় বিষয় কেন বললেন?\n" +
				"৩. ভগবান পরে আবার নিষ্কামকর্মের ইঙ্গিত দিলেন কেন?");
		addVideoItem("3VY720sh9ww", "কিভাবে বুদ্ধিমান হওয়া যায়?|| গীতামৃত কোর্স (পর্ব-13) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. সাংখ\u200D্যদর্শন কি?\n" +
				"২. তিন প্রকার সাংখ\u200D্য কি কি এবং তাদের বিষয়বস্তু কি?\n" +
				"৩. গীতার ২/৩৯ নং শ্লোকে কোন সাংখ\u200D্যের কথা বলা হয়েছে?\n" +
				"৪. বুদ্ধিযোগ কি?");
		addVideoItem("0X4jfeZs5E0", "হিন্দু কে কি বেদ পড়তেই হবে? || গীতামৃত কোর্স (পর্ব-14 ) Bhagavad Gita class\n", "\n" +
				"১.'নির্যোগক্ষেম'- শব্দের অর্থ কি?\n" +
				"২. জীবতত্ত্বের মধ\u200D্যে কারা কর্মযোনী এবং কারা ভোগযোনীর অর্ন্তগত?\n" +
				"৩. পরিশ্রম করেও অনেক সময় ফলাফল ভালো হয় না কেন?");
		addVideoItem("TK6Ql0-1810", "বৈষ্ণবদের কি শ্রাদ্ধ তর্পন করা উচিত? || গীতামৃত কোর্স (পর্ব-15) Bhagavad Gita class", "১. বহু জীবনের কর্মবন্ধন কিভাবে খুলে যায়?\n" +
				"২. 'প্রচার' - নিষ্কামকর্মের বড়ো সুযোগ।\n" +
				"২. মোহরূপ গভীর অরণ\u200D্যকে অতিক্রম করতে গেলে কার শরনাগত হতে হয়?");
		addVideoItem("AZZxZn07twc", "দুর্বাসা ও বিশ্বামিত্র মুনির করুন কাহিনী || গীতামৃত কোর্স (পর্ব-16) Bhagavad Gita class\n", "১. মুনি কারা?\n" +
				"২. স্থিতধী ও অস্থিতধী মুনির পার্থক্য।\n" +
				"৩. ক্রোধ ও ভয় এগুলো কাদের ক্ষেত্রে হয়?");
		addVideoItem("CX7CeSuZwq8", "ভগবত প্রসাদ গ্রহনের আশ্চর্য ফল? || গীতামৃত কোর্স (পর্ব-17) Bhagavad Gita class\n", "১. ভক্তের অধঃপতন হয় কিভাবে?\n" +
				"২. অধঃপতন রোধ করার উপায়।\n" +
				"৩. কৃষ্ণভক্তরা দুঃখে অবিচলিত থাকেন কিভাবে?");
		addVideoItem("lbgFx6BxdU0", "কে প্রকৃত শান্তি লাভ করেন? || গীতামৃত কোর্স (পর্ব-18) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. ভগবান অর্জুনকে \"মহাবাহো\" বলে সম্মোধন করলেন কেন?\n" +
				"২. জড় ইন্দ্রিয়ের  ভোগবাসনারূপ শত্রুকে দমন করতে কোন শক্তির প্রয়োজন এবং কিভাবে তা লাভ করা যায়?\n" +
				"৩. কৃষ্ণভক্ত ও বিষয়ীর কাছে কোন বিষয়গুলি দিন ও রাতের মতো?");
		addVideoItem("KoIIhbmGNfk", "বিষাদ ও সাংখ্য যোগের সারকথা | রিভিশন ক্লাস | গীতামৃত কোর্স (পর্ব-19) Bhagavad Gita class\n", "১ম অধ\u200D্যায় বিষাদযোগে অর্জুন তার অন্তরঙ্গ আত্মীয়স্বজন, বন্ধুবান্ধব ও আচার্যবর্গদের সঙ্গে যুদ্ধ করতে হবে জেনে বিষাদগ্রস্ত হয়ে পড়েছিলেন এবং শোকে, দুঃখে কাতর হয়ে যুদ্ধ করার সংকল্প পরিত\u200D্যাগ করেছিলেন।");
		addVideoItem("H58bO7NuBag", "ভগবত গীতা কি দ্বর্থ-বোধক? || গীতামৃত কোর্স (পর্ব-20) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. কর্ম ও বুদ্ধির বিষয়ে ভগবানের বাক\u200D্যে অর্জুনের বিভ্রান্তি।\n" +
				"২. শাস্ত্রজ্ঞানহীন ভক্তি অনুশীলনে ভক্তিপথ থেকে বিচ\u200D্যুতির সম্ভাবনা।\n" +
				"৩. শ্রীল প্রভুপাদ গীতার প্রতি পৃষ্ঠায় ভক্তির কথা বলেছেন কেন?");
		addVideoItem("XPxnMVMIbns", "বিনা অর্থে যজ্ঞ কিভাবে করব? || গীতামৃত কোর্স (পর্ব-21) Bhagavad Gita class by HG Sumitra krishna Das\n", "\n" +
				"১. বর্ণাশ্রম ধর্ম আচরণের গুরুত্ব।\n" +
				"২. ভন্ড সাধুর থেকে কর্মনিষ্ঠ মেথর শ্রেষ্ঠ!!\n" +
				"৩. যজ্ঞ কে?");
		addVideoItem("UGDsnBFfgrI", "ঈর্ষা কিভাবে জয় করবেন? || গীতামৃত কোর্স (পর্ব-22) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. ভগবানের দৃষ্টিতে চোর কারা?\n" +
				"২. ভগবানকে নিবেদন না করে নিজে অন্ন গ্রহন করলে কি হয়?\n" +
				"৩. কর্মচক্রটি কি? কর্মচক্রের বিস্তারিত বর্ননা।");
		addVideoItem("ZJDgWGyPnmE", "কিভাবে বুঝব, কোনটি কৃষ্ণের ইচ্ছা ? || গীতামৃত কোর্স (পর্ব-23) Bhagavad Gita class\n", "১. বিষয়ভোগী মানুষদের যজ্ঞ অনুষ্ঠান করার আবশ\u200D্যকতা।\n" +
				"২. একজন সৎ ব\u200D্যক্তি কখন পাপী হতে পারে?\n" +
				"৩. আত্মারাম কারা?");
		addVideoItem("aKHhcVP2KWw", "ভগবান কেন কাজ করেন ? || গীতামৃত কোর্স (পর্ব-24) Bhagavad Gita class by HG Sumitra krishna Das\n", "১. শ্রেষ্ঠ ব\u200D্যক্তির কর্তব\u200D্য কি?\n" +
				"২. পরমেশ্বর ভগবান সাধারণ মানুষের মতো কঠোর পরিশ্রম করেন কেন?\n" +
				"৩. সুশৃঙ্খল সমাজ গঠনের উপায়।");
		addVideoItem("ZWdy4kRFBRo", "গীতামৃত কোর্স (পর্ব-25) Bhagavad Gita class by HG Sumitra krishna Das\n", "\n" +
				"ভক্ত বড় না ভগবান বড় ??\n" +
				"https://youtu.be/QaukyGoWgi8 \n" +
				"\n" +
				"ভগবান শ্রীকৃষ্ণ কি দেহত্যাগ করেছিলেন??");
		addVideoItem("JDEYbYS1e3A", "গীতামৃত কোর্স (পর্ব-26) Bhagavad Gita class by HG Sumitra krishna Das", "আপনারা যদি ভগবদ্গীতা , ভাগবত ক্লাসে সরাসরি যোগদান করতে চান তাহলে কমেন্টে আপনাদের হোয়াটসঅ্যাপ নাম্বার দিতে পারেন অথবা নিচের নাম্বারে হোয়াটসঅ্যাপ করে যোগাযোগ করতে পারেন: 9038028684");

		//addVideoItem("", "", "");

		                       //====Category Item Name01

		createPlayListForVideo("গীতামৃত কোর্স", R.drawable.gita_01);

		//========================Video Item Category End


		//**********************************************************
		//**********************************************************
		//**********************************************************

		//========================Video Item Category2
		addVideoItem("w94rTgBmfGo", "ভাগবতের মহিমা || ভাগবতামৃত কোর্স (পর্ব-1) Bhagabat class By Sumitra krishna Das\n", "1.শাস্ত্র কোনটি?\n" +
				"2.শ্রীমদ্ভাগবত রচিত হয়েছিল কেন?\n" +
				"3.শ্রীমদ্ভাগবতের কোন স্কন্ধ ভগবানের চিন্ময় দেহের 4.কোন অংশকে সূচিত করে?");
		addVideoItem("5IjahodCkHA", "ভাগবত ধর্ম কি? || ভাগবতামৃত কোর্স (পর্ব-2) Bhagabat class By Sumitra krishna Das\n", "1.ভগবান কে?\n" +
				"2.আপেক্ষিক সত\u200D্য কি?\n" +
				"3.পরম সত\u200D্যের describtion।");
		addVideoItem("nV0Ktk2oGOQ", "সমস্ত বেদ শাস্ত্রের সারতত্ত্ব কি? || ভাগবতামৃত কোর্স (পর্ব-3) Bhagabat class By Sumitra krishna Das\n", "1.বৈদিক কল্পবৃক্ষের সুপক্ক ফল কোনটি?\n" +
				"2.যে কোনো বক্তার কাছ থেকে শ্রবণ করে কি 3.ভাগবতের অমৃতময় রস আস্বাদন করা যায়?\n" +
				"4.মুখ\u200D্যরস ও গৌনরসের পার্থক\u200D্য");
		addVideoItem("9KT11K0aUws", "কার কাছে ভাগবত শ্রবন করতে হয়? || ভাগবতামৃত কোর্স (পর্ব-4) Bhagabat class By Sumitra krishna Das", "1.ঋষিদের দ্বারা সূত গোস্বামীর গুনমহিমা কীর্তন তথা 2.ব\u200D্যাসাসনে বসার যোগ\u200D্যতা\n" +
				"3.কলিযুগের ৪ টি মুখ\u200D্য পাপকর্ম\n" +
				"4.পারমার্থিক উন্নতিতে শ্রবণ-কীর্তনের গুরুত্ব");
		addVideoItem("n-g6s5VwRgI", "পাপ-মুক্ত ও ভয়-মুক্ত হওয়ার পন্থা || ভাগবতামৃত কোর্স (পর্ব-5) Bhagabat class By Sumitra krishna Das\n", "1.আপাত সত\u200D্য ও নিত\u200D্য সত\u200D্য কোনটি?\n" +
				"2 সাত্বতাং পতিঃ' কে?\n" +
				"3.ভগবানের কথা কেমন করে কীর্তন করতে হয়?");
		addVideoItem("LgzJ9DJN5AQ", "কিভাবে সহজেই সৎ গুনের অধিকারী হবেন? ||ভাগবতামৃত কোর্স (পর্ব-6) Bhagabat class By Sumitra krishna Das\n", "1. ভগবানের লীলাবিলাসকে পরম মঙ্গলময় বলা হয়েছে কেন?\n" +
				"2. কোন গ্রন্থ পাঠ করলে পদে পদে মধুর স্বাদ আস্বাদন করা যায়?\n" +
				"3.ভক্তরা জড় পত্রিকা পড়েন নাকি চিন্ময় পত্রিকা পড়েন? ");
		addVideoItem("-l0iXXqgatk", "সম্পূর্ণরুপে সন্তুষ্ট হবার উপায় কি?|ভাগবতামৃত কোর্স (পর্ব-7) Bhagabat class By Sumitra krishna Das\n", "১. মনোযোগ সহকারে শ্রবনের গুরুত্ব।\n" +
				"২. কিভাবে প্রশ্ন করলে শ্রীগুরুদেব সন্তুষ্ট হন?\n" +
				"৩. দুঃখময় সংসার থেকে কিভাবে মুক্ত হওয়া যায়?");
		addVideoItem("p15U6EDGunY", "ভক্তি করলে আমরা কি কি পাবো ? | ভাগবতামৃত কোর্স (পর্ব-8) Bhagabat class By HG Sumitra krishna Das\n", "১. প্রবৃত্তি ও নিবৃত্তি মার্গ কি?\n" +
				"২. অধোক্ষজ কে?\n" +
				"৩. ভগবানের কাছে কি চাইতে হয়?");
		addVideoItem("V5rH5N7xxUc", "শ্রেষ্ঠ কর্তব্য কি? | ভাগবতামৃত কোর্স (পর্ব-9) Bhagabat class By HG Sumitra krishna Das\n", "\n" +
				"১. অদ্বয় জ্ঞান কোনটি?\n" +
				"২. বিভিন্ন উদাহরণের মাধ\u200D্যমে ভগবানের ত্রিবিধ প্রকাশের (ব্রম্ম, পরমাত্মা ও ভগবান ) ব\u200D্যাখ\u200D্যা।\n" +
				"৩. মুনিরা কিভাবে পরমাত্মারূপে তত্ত্ববস্তুর দর্শন করতে পারেন?");
		addVideoItem("pRbTc3T2QvM", "মনের ময়লা কিভাবে দূর হবে ? | ভাগবতামৃত কোর্স (পর্ব-10) Bhagabat class by Sumitra krishna Das\n", "1. ভক্তরা কর্মবন্ধন থেকে কিভাবে মুক্ত হন?\n" +
				"2. জ্ঞান ও কর্মের সাথে কি মেশালে তা জ্ঞানযোগ ও কর্মযোগ হয়ে যায়?\n" +
				"3. শুদ্ধভক্তের সেবার মাহাত্ম\u200D্য।");
		addVideoItem("2sLF5OdnGBE", "গুনের পরিবর্তন ঘটানো কিভাবে সম্ভব? | ভাগবতামৃত কোর্স (পর্ব-11) Bhagabat class\n", "১. কাম, ক্রোধ, লোভ আদি জড় কলুষ থেকে সহজে মুক্তি লাভের উপায়।\n" +
				"২. সত্ত্ব ও শুদ্ধসত্ত্বের পার্থক্য কি? কোন স্তরে পরমেশ্বর ভগবানকে জানা যায়?\n" +
				"৩. ভক্তিতে উন্নতির ৯ টি ধাপ কি কি?");
		addVideoItem("VhypSTJ0Vzk", "ব্রহ্মা, বিষ্ণু ও শিবের মধ্যে কে শ্রেষ্ঠ ? | ভাগবতামৃত কোর্স (পর্ব-12) Bhagabat class\n", "১. বেদশাস্ত্রে দেবদেবীদের পূজা করার উল্লেখ রয়েছে কেন?\n" +
				"২. শ্রীকৃষ্ণ থেকে কিভাবে শ্রীবিষ্ণুর প্রকাশ হয়েছে?\n" +
				"৩. ভগবদ্ভক্তি অনুশীলনের যোগ\u200D্যতা কি?");
		addVideoItem("cO9JJqHBHGw", "ইসকন ভক্তরা বেদপাঠ করেন না কেন? | ভাগবতামৃত কোর্স (পর্ব-13) Bhagabat class By HG Sumitra krishna Das\n", "১. সমস্ত বেদের মূলতত্ত্ব কি? বেদপাঠবিহীন ইস্কনের পন্থা কি সঠিক?\n" +
				"২. বিশ্বসৃষ্টির রহস\u200D্য।\n" +
				"৩. জড় জগত কিভাবে সক্রিয় হয়?\n" +
				"৪. জীব চিন্ময় ধাম থেকে জড় জগতে কিভাবে এসেছে?");
		addVideoItem("XJjCuR94zV8", "ব্রহ্মান্ড সৃষ্টির অজানা রহস্য!!| ভাগবতামৃত কোর্স (পর্ব-14) Bhagabat class By HG Sumitra krishna Das\n", "১. জড় সৃষ্টির ১৬ টি তত্ত্ব কি কি?\n" +
				"২. পুরুষাবতারের বিস্তারিত বর্ণণা।\n" +
				"৩. মহাবিষ্ণু যে সাগরে শয়ন করেন সেটিকে  কারণসাগর বলা হয় কেন?");
		addVideoItem("-yN-yWeAwN8", "ভগবান শূকরের রুপ নিলেন কেন? | ভাগবতামৃত কোর্স (পর্ব-15) Bhagabat class By HG Sumitra krishna Das\n", "১. প্রেমোময়ী ভগবানকে কখন সর্বত্র দর্শন করা যায়?\n" +
				"২. কিভাবে প্রেমচক্ষুর বিকাশ হয়?\n" +
				"৩. সমগ্র জড় জগতের পরমাত্মা কে?");
		addVideoItem("aC7d29HfK8c", "গুরুদেবকে কখন ত\u200D্যাগ করা যায় ? | ভাগবতামৃত কোর্স (পর্ব-16) Bhagabat class By HG Sumitra krishna Das\n", "১. দত্তাত্রেয়রূপে ভগবানের আর্বিভাব।\n" +
				"৩. ব্রম্মার ১২ ঘন্টা = কত বছর?\n" +
				"৪. ব্রম্মার মোট আয়ুষ্কাল = কত বছর? (পৃথিবীর হিসাবে)");
		addVideoItem("3Ov1g7COI38", "সমুদ্রমন্থনের কাহিনী | ভাগবতামৃত কোর্স (পর্ব-17) Bhagabat class By HG Sumitra krishna Das\n", "১. দেবতা ও অসুরদের সমুদ্র মন্থনের কাহিনী।\n" +
				"২. ভগবান কি পক্ষপাতিত্ব করেন?\n" +
				"৩. কোন গুরুদেবকে ত\u200D্যাগ করা যায়?");
		addVideoItem("-i6jgySieE8", "বুদ্ধ,শংকর,চৈতন্য দেবের এত ভিন্নমত কেন? | ভাগবতামৃত কোর্স (পর্ব-18) Bhagabat class\n", "১. শ্রীমদ্ভাগবতের প্রামানিকতা।\n" +
				"২. বুদ্ধদেব, শঙ্করাচার্য\u200D্য এবং শ্রীচৈতন\u200D্যদেবের ভিন্ন ভিন্ন মত কেন?\n" +
				"৩. কল্কি অবতারের ভবিষ\u200D্যদ্বাণী।");
		addVideoItem("BGW1NFm9V_8", "শুধু ভাগবত পড়লেই সব দুঃখ থেকে মুক্তি লাভ হয় || ভাগবতামৃত কোর্স (পর্ব-19) Bhagabat class\n", "১. সমস্ত দুঃখ-দুর্দশা থেকে মুক্ত হওয়ার উপায় তথা ভাগবতের মাহাত্ম\u200D্য।\n" +
				"২. ভগবানের বিরাট রূপকে জড় বলা হয় কেন?\n" +
				"৩. নবীন ভক্তদের ভগবানের বিরাট রূপের ধারণা করতে বলা হয় কেন?");
		addVideoItem("r97IZNqWaEI", "প্রতিকূল সেবাটি কিরকম? | ভাগবতামৃত কোর্স (পর্ব-20) Bhagabat class By HG Sumitra krishna Das\n", "১. ভগবানের আর্বিভাব বা তথাকথিত জন্ম কি সাধারণ মানুষের মতো?\n" +
				"২. ভগবানের লীলা কারা বুঝতে পারেন?\n" +
				"৩. নাস্তিকেরা কিভাবে শ্রীকৃষ্ণের সেবা করেন?");
		addVideoItem("dSdZOTx5jcY", "ভাগবত মাহাত্ম্য | ভাগবতামৃত কোর্স (পর্ব-21) Bhagabat class By HG Sumitra krishna Das\n", "১. ভাগবত কেমন করে পড়লে ভগবানের পরম আর্শিবাদ লাভ করা যায়?\n" +
				"২. শাস্ত্রে বর্ণিত কাহিনী কি কাল্পনিক?\n" +
				"৩. অমৃতময় ভাগবত কথা কার কাছ থেকে শ্রবন করতে হয়?");
		addVideoItem("pZMSuGbZSPM", "ভাগবত-কথা বলার কৌশল | ভাগবতামৃত কোর্স (পর্ব-22) Bhagabat class By HG Sumitra krishna Das\n", "১. হরিকথা বলার কৌশল।\n" +
				"২. হরিকথা শ্রবনের আগে \"হরে কৃষ্ণ\" মহামন্ত্র কীর্তন করতে হয় কেন?\n" +
				"৩. কারা ভাগবত কথা বলার অনুপোযুক্ত?");
		addVideoItem("0Dgmk5S0YAM", "ভক্তের সাথে কিভাবে কথা বলতে হয় ? | ভাগবতামৃত কোর্স (পর্ব-23) Bhagabat class\n", "১. সাধুকে কিভাবে চেনা যায়?\n" +
				"২. শ্রীমদ্ভাগবত কি শ্রুতি নাকি স্মৃতিশাস্ত্র?\n" +
				"৩. ভক্তদের অন\u200D্য ভক্তদের সাথে কিভাবে কথা বলতে হয়?");
		addVideoItem("_YNfwbgolKM", "অভাবের প্রকৃত কারন কি? | ভাগবতামৃত কোর্স (পর্ব-24) Bhagabat class By HG Sumitra krishna Das\n", "১. খাদ\u200D্যসংকটের প্রকৃত কারণ কি?\n" +
				"২. মানুষসহ সমস্ত জীবের মঙ্গলসাধনের জন\u200D্য কেমন রাজার প্রয়োজন?\n" +
				"৩. বৈদিক মন্ত্র নাকি তত্ত্বজ্ঞান উপলব্ধি কোনটি অধিক গুরুত্বপূর্ণ।");
		addVideoItem("i8RtKXMfRpU", "কলিযুগ সম্পর্কে ভয়ংকর ভবিষৎবানী| ভাগবতামৃত কোর্স (পর্ব-25) Bhagabat class By HG Sumitra krishna Das\n", "১. কলিযুগের ভয়ংকর ভবিষ্যৎ বানী।\n" +
				"২. কলির প্রভাব থেকে রক্ষার উপায়।\n" +
				"৩. পঞ্চমবেদ কি? রামায়ন, মহাভারত কি কাল্পনিক?");
		addVideoItem("IJ4akEqX_NY", "বৈদিক সংস্কার গ্রহনের পদ্ধতি | ভাগবতামৃত কোর্স (পর্ব-26) Bhagabat class By HG Sumitra krishna Das\n", "1.  যজ্ঞের জন\u200D্য ৪ জন দক্ষ ব্রাম্মন কারা?\n" +
				" 2.  বেদের বিভিন্ন ভাগ, উপভাগ। \n" +
				" 3.  বিভিন্ন বেদ বিভিন্ন তত্ত্বজ্ঞানী মুনিঋষিদের কাছে অর্পন।");

		//addVideoItem("", "", "");


		                       //====Category Item Name02

		createPlayListForVideo("ভাগবতামৃত কোর্স", R.drawable.vagvot);
        //========================Video Item Category End2
		//==========================================================================*





		//========================Video Item Category3
		addVideoItem("aIQEr1bliyo", "শ্রী কৃষ্ণের বাল্যলীলা", " Album : Sree Krishner Ballya Lila Singer : Archana Das Lyrics : Traditional");
		addVideoItem("OfYsTFx3euA", "Krishna Sudama Bengali Krishna Lila Kirtan", "● Album : Krishna Sudama Song : Krishna Sudama ● Singer : Archana Das ● Lyrics :  Traditional\n");
		addVideoItem("bFbbaef6TLs", "যে কাহিনী বলতে গিয়ে দিদি নিজেই কাঁদলেন", "জীবনে একবার হলেও শুনুন - Krishna Pal Kirtan");
		addVideoItem("RLAkrq2ZLWk", "মৌমিতা ঘোষ কীর্তন moumita ghosh kirtan 2022", "কেবলম কীর্তন পালা কীর্তন নতুন কীর্তন গান  নাম কীর্তন  ইসকনের কীর্তন অধিবাস কীর্তন | শম্পা গোস্বামী কীর্তন | ");
		addVideoItem("LugW2kV9G24", "হরে শব্দের অর্থ কি! জেনিন”নতুন তত্ত্বকথা’ পাঠক–গোপীনাথ ব্রহ্মচারী", "হরে কৃষ্ণ। বর্তমান প্রেখাপটে গুরুদেবের  ভাগবতপাঠ খুবই গুরুত্বপূর্ণ। গুরুদেবকে শতকোটি  প্রনাম");
		addVideoItem("4Fxr_xi2iUM", "যে ঘরের নারী ধর্ম শিলা হয় না সেই ঘরে যা হয়। যে কথা বললেন” শ্রী গোপীনাথ দাস ব্রহ্মচারী", "যে ঘরের নারী ধর্ম শিলা হয় না সেই ঘরে যা হয়। যে কথা বললেন” শ্রী গোপীনাথ দাস ব্রহ্মচারী");
		addVideoItem("LxKKfg02QKU", "পেয়াজ,রসুন কেন খেতে নেই।শ্রীশ্রী গোপীনাথ দাস ব্রহ্মচারী গুরুমহারাজ\n", "পেয়াজ,রসুন কেন খেতে নেই।শ্রীশ্রী গোপীনাথ দাস ব্রহ্মচারী গুরুমহারাজ\n");
		addVideoItem("FlWP_iwjR7c", "পহেলা বৈশাখে ইলিশ মাছ খাইলে কি হয়?শ্রী গোপীনাথ দাস ব্রহ্মচারী।\n", "পহেলা বৈশাখে ইলিশ মাছ খাইলে কি হয়?শ্রী গোপীনাথ দাস ব্রহ্মচারী।\n");
		addVideoItem("dN19JHmPNdk", "গর্ভপাত করলে কী পাপ হয় গর্ভপাত কেনো করিতে নেই হিন্দু মুসলিম সবাই কে শ্রবণ করা উচিত তত্ত্বকথা", "হিন্দু মুসলিম সবাই কে শ্রবণ করা উচিত আপনার পাঠ শুনলে মনে অনেক শান্তি আসে তত্ত্বকথা");
		addVideoItem("nfTmLA1bIDc", "কবি গানের লড়াই II নারী বড়ো না পুরুষ বড়ো II অসীম সরকার II মনোরঞ্জন সরকার II নতুন কবি গান 2020\n", "শিল্পীর বাউল , লোকগীতি , ফোল্ক , ভক্তি গীতি , লালন , বিজয় সরকার , কবি গান , স্টেজ প্রোগ্রামের , কৃষ্ণা নাম , কীর্তন গান , মাটির গান, লোকসংগীত , ভাটিয়ালি , বাংলাদেশী গান , জনপ্রিয় গান পাবেন ");
		addVideoItem("Z3wbipkYN2E", "অসীম সরকার নতুন কবি গান / asim sarkar new kobi gaan / asim sarkar kobi", "কবিয়াল অসিম সরকারের কবি শুনে  বাংলাদেেশের কবিয়াল তারিনী সরকার, কবিয়াল কালশশী সরকার  ঢাকা কথা মনে পড়ে। কবিয়াল শ্রদ্ধেয় বিজয় সরকারের স্মৃতি এদের মাধ্যমে বাংলায় অমর হয়ে  থাকুক এ প্রার্থনা করছি।");
		addVideoItem("6HXz-X_RmVs", "[সম্পা গোস্বামীর কীর্তনটি ১০ দিন কান্না থামবে না][Sampa Goswami kirton2022][সম্পা গোস্বামী কীর্তন]\n", " Thanks for visiting Our Youtube channel. Sampa Goswami New Kirtan 2022 সম্পা গোস্বামী নতুন কীর্তন 2022");
		addVideoItem("c2D75OsWGTE", "কায়দা আসে\nহাসি পেয়েছে।\n", "Ashim Sarkar Bengali Kavigan story\n" +
			 	"অসীম সরকারের কবিগান\n" +
				"kobi gan");
		addVideoItem("eiaQkHRhpBo", "নতুন চারটি হাসির গল্প | বিউটি পার্লার, শশ্মান ঘাট, প্রেম, ভাত দাও | কবি অসীম সরকার| Kabi Asim Sarkar\n", "নতুন চারটি হাসির গল্প | বিউটি পার্লার, শশ্মান ঘাট, প্রেম, ভাত দাও | কবি অসীম সরকার| Kabi Asim Sarkar\n");
		addVideoItem("Zq6cJeoMIbE", "প্রকৃত সদ্ গুরু কোথায় পাব?Who is bonafide Guru??\n", "সদ্ গুরুর যোগ্যতা কি? সদ্ গুরু\u200C কোথায় পাওয়া যা\u200Cবে?\u200Cকেন দীক্ষা\u200C নি\u200Cতে হ\u200Cবে ।এই সমস্ত গুরুত্বপূর্ণ প্র\u200Cশ্নের উত্তর জানুন!\n" +
				"শ্রীপাদ গোপীকান্ত দাস ব্রহ্মচারী মায়াপুর ইন\u200Cষ্টি\u200Cটিউ\u200Cটের এক জন সিনিয়র শিক্ষক , আধ্যাত্মিক অনুপ্রেরক, শাস্ত্রীয় সিদ্ধান্তের উপস্থাপক, পারমার্থিক পরামর্শক।");
		addVideoItem("s-uvhg2K4F4", "হিন্দু ধর্মের ইতিহাস || আপনি কি হয়ে জন্মগ্রহন করেন হিন্দ- মুসলিম-খিস্টান জানুন প্রকৃত সত্য\n", "হিন্দু ধর্মের ইতিহাস || আপনি কি হয়ে জন্মগ্রহন করেন হিন্দ- মুসলিম-খিস্টান জানুন প্রকৃত সত্য\n");
		addVideoItem("aDZ5UMzmk_k", "ঈশ্বর এক হলে ধর্ম আলাদা কেন? Swami vivekananda | Ramkrishna | Sarada Maa | Swamiji\n", "ঈশ্বর এক হলে ধর্ম আলাদা কেন? Swami vivekananda | Ramkrishna | Sarada Maa | Swamiji\n");
		addVideoItem("V_1Z82qHCsw", "বিবেকানন্দর মতো স্মৃতিশক্তি পেতে হলে | রামকৃষ্ণ ও বিবেকানন্দ | Ramkrishna bani। sarada ma\n", "রামকৃষ্ণ ও সারদা মা। ১০ টি উপদেশ। Ramkrishna bani। sarada ma। by GyanGuy\n" +
				"10 international quotes of Saradamani & Ramkrishna Paramhansa Deb bani in bengali");
		addVideoItem("kNVx3H11k80", "আগুনের নাম বিবেকানন্দ | রামকৃষ্ণ ও Vivekananda | Ramkrishna bani। sarada ma\n", "আগুনের নাম বিবেকানন্দ | রামকৃষ্ণ ও Vivekananda | Ramkrishna bani। sarada ma\n");
		addVideoItem("QJFL4PM_rAo", "Golper Ashor || Loknath Babar Aloukikk Kahini || Loknath Baba || Baba Loknath Brahmachari ||\n", "জয় বাবা লোকনাথ শতকোটি প্রনাম জানাই তব শ্রীচরনে কৃপা কর প্রভু ভক্তি দাও বিশ্বাস দাও নির্ভরতা দাও ।\n");
		addVideoItem("V5ksBDhUyBQ", "তুলসী মহিমা এবং তুলসী ব্রত কথা।\nরাধে রাধে প্রভু অপরাধ ক্ষমা করবেন", "রাধে রাধে প্রভু অপরাধ ক্ষমা করবেন।ঐ বার বার খুব মন দিয়ে শুনবেন এটা শুনতে ভালো লাগে না। কারণ আপনার পাঠ যারা শোনে তারা মনে হয় মন দিয়েই শোনে। পুনরায় আবার খমা প্রার্থনা চাইছি।\n");
		addVideoItem("77msJl45sHc", "অসীম সরকার নতুন কবি গান | asim sarkar new kobi gaan | asim sarkar kobi gaan 2022 | Kobi Gaan 2022\n", "অসীম সরকার নতুন কবি গান | asim sarkar new kobi gaan | asim sarkar kobi gaan 2022 | Kobi Gaan 2022\n");
		addVideoItem("90GviwvLEvk", "মানুষের মৃত্যুর পর ঠিক কী হয় ? What happens when you die? শ্রীকৃষ্ণের গীতা জ্ঞান\n", " খুব সুন্দর লাগলো,, ভগবান আপনার মঙ্গল করুন।। জয় মা।");
		addVideoItem("7G3OazC8b2c", "মানুষের পূর্ব জন্মের কথা কিছুই মনে থাকে না কেন? | Why don't We remember anything from Past Life\n", "মানুষের পূর্ব জন্মের কথা কিছুই মনে থাকে না কেন? | Why don't We remember anything from Past Life\n");
		addVideoItem("qJXSrVrVEcA", "অভিমন্যুর বীরত্ব কথা মৃত্যুর সময় তার পিতা অর্জুন কে যে ভাবে শুনালো মহাভারত বাংলা ।| Piu Tech ||\n", "অভিমন্যুর বীরত্ব কথা মৃত্যুর সময় তার পিতা অর্জুন কে যে ভাবে শুনালো মহাভারত বাংলা ।| Piu Tech ||\n");
		addVideoItem("MZ6tafKMrEs", "Krishna Katha || কৃষ্ণ কথা || Mahabharat Srikrishna Bani in Bengali - মহাভারত শ্রীকৃষ্ণ বাণী", "Krishna Katha || কৃষ্ণ কথা || Mahabharat Srikrishna Bani in Bengali - মহাভারত শ্রীকৃষ্ণ বাণী");
		addVideoItem("zYIi05D7ukU", "শ্রীকৃষ্ণের ১০৮ নাম (কথাসহ) ।। 108 Names of Lord Sri Krishna with Lyrics\n", "শ্রীকৃষ্ণের ১০৮ নাম (কথাসহ) ।। 108 Names of Lord Sri Krishna with Lyrics\n");
		addVideoItem("K1zNIQ76fGE", "যখন শ্রীকৃষ্ণ নিলেন কর্ণ ও যুধিষ্ঠীরের দানশীলতার পরীক্ষা | Story Of Daanveer Karna\n", "১। শ্রীনন্দ রাখিল নাম নন্দের নন্দন।\n" +
				"২। যশোদা রাখিল নাম যাদু বাছাধন।\n" +
				"৩। উপানন্দ নাম রাখে সুন্দর গোপাল।");
		addVideoItem("J6F5-oO63VA", "ভগবান পরশুরাম নিজের মায়ের হত্যা কেন করলেন ? Puran Rahasya ।\n", "ভগবান পরশুরাম নিজের মায়ের হত্যা কেন করলেন ? Puran Rahasya ।\n");
		addVideoItem("982_sTsowqc", "পাণ্ডবদের অন্তিম যাত্রা! যুধিষ্ঠির কিভাবে পেলেন স্বর্গ? | Pandavas Journey to Heaven\n", "কি বিষ্ময়কর মহাভারতেরগল্প? তা শুনে এবং দেখে আমি অভিভুত। বেশ ভাল লাগল। জয় শ্রীকৃষ্ণ।\n");
		addVideoItem("-DaUkbHNzuY", "মধুর হরিনাম সংকীর্তন || Modhur Hori Naam Sankirtan || হরে কৃষ্ণ হরে রাম || Hare Krishna Hare Rama\n", "মধুর হরিনাম সংকীর্তন || Modhur Hori Naam Sankirtan || হরে কৃষ্ণ হরে রাম || Hare Krishna Hare Rama");
		addVideoItem("fPe9PC0cVmc", "বেল গাছের মাহাত্ম্য//শ্রী বিষ্ণুপদ দাস\nপ্রনাম গ্রহণ করবেন মহারাজ। \n" +
				"বিলব্ পত্রের মাহাত্ম্য জেনে খুব ই আনন্দিত হলাম।", "পবিলব্ পত্রের মাহাত্ম্য জেনে খুব ই আনন্দিত হলাম। এত সুন্দর ঘটনা জেনে সমৃদ্ধ  হলাম।\n" +
				"জয় ভগবানের জয়, জয় মহাদেবের জয়, জয় বিলব্ প্রতর জয়।");
		addVideoItem("z0jvtPc4BWE", "মধুর কৃষ্ণ নাম শুনে দেখুন ", "সুমধুর হরে কৃষ্ণ নাম বাংলা লীলা কীর্তন, ভাগবত, কৃষ্ণ কথা প্রভু দণ্ডবৎ আপনি কি অমৃতময় বাণী শোনালে কিন্তু যা হয়ে গেছে তার কি উপায় আছে কত মানুষের শিক্ষা হয়ে যাবে হরেকৃষ্ণ");
		addVideoItem("z0jvtPc4BWE", "মধুর কৃষ্ণ নাম শুনে দেখুন ", "সুমধুর হরে কৃষ্ণ নাম বাংলা লীলা কীর্তন, ভাগবত, কৃষ্ণ কথা প্রভু দণ্ডবৎ আপনি কি অমৃতময় বাণী শোনালে কিন্তু যা হয়ে গেছে তার কি উপায় আছে কত মানুষের শিক্ষা হয়ে যাবে হরেকৃষ্ণ");
		addVideoItem("", "", "");

		                         //====Category Item Name03

		createPlayListForVideo("কৃষ্ণ লীলা", R.drawable.shiv);
		//==========================================================================*
		//==========================================================================*

		//=========================Category for website loader

		createCategoryForWebsite("পবিত্র বেদ", R.drawable.book, "https://bookishbd.com/the-holy-ved-all-part-in-bangla");
		//=========================04
		//=========================
		createCategoryForWebsite("সনাতন ধর্মতত্ত্ব", R.drawable.namaste, "https://sanatandharmatattva.wordpress.com");
		//=========================05
		//=========================
		createCategoryForWebsite("গীতা লাইব্রেরী", R.drawable.library, "https://www.ebanglalibrary.com/category/%E0%A6%AC%E0%A6%BE%E0%A6%82%E0%A6%B2%E0%A6%BE-%E0%A6%97%E0%A7%80%E0%A6%A4%E0%A6%BE/");
		//=========================06
		//=========================
		createCategoryForWebsite("আদি শিক্ষা", R.drawable.teachers, "https://adisikha.com");
		//=========================07  https://www.bkash.com/terms-and-conditions
		//=========================
		createCategoryForWebsite("All Newspaper", R.drawable.allnews_100, "http://www.allbanglanewspapersbd.com/daily-bangla-newspaper");
		//=========================08
		//=========================
		createCategoryForPDF("Culture", R.drawable.category_4, "merged.pdf");
		//=========================09
		//=========================
		createCategoryForWebsite("World Power", R.drawable.earth, "https://www.youtube.com/watch?v=uTj-sc9pV7Y");
		//=========================10
		//=========================
		createCategoryForWebsite("Age Database", R.drawable.calculator, "https://www.easycalculation.com/date-day/age-calculator.php");
		//=========================11
		//=========================
		createCategoryForWebsite("BMI Check", R.drawable.bmi_calculetor, "BMI");
		//=========================12
		//=========================
		createCategoryForWebsite("ThumbLoader", R.drawable.download, "ThumbLoader");
		//=========================13
		//=========================
		createCategoryForWebsite("MyTube", R.drawable.tube, "MyTube");
		//=========================14
		//=========================
		createCategoryForWebsite("Popular", R.drawable.viral, "Popular");
		//=========================15
		//=========================
		createCategoryForWebsite("Weather", R.drawable.weather, "Weather");
		//=========================16
		//=========================
		createCategoryForWebsite("SMS", R.drawable.bani, "SMS");
		//=========================17
		//=========================
		createCategoryForWebsite("পঞ্চতত্ত্ব", R.drawable.five_digit, "PanchaTattva");
		//=========================18
		//=========================
		createCategoryForWebsite("মন্ত্র ও ব্রত", R.drawable.manta, "Vow");
		//=========================19
		//=========================
		createCategoryForWebsite("Ekadashi", R.drawable.donation, "Ekadashi");
		//=========================20
		//=========================
		createCategoryForWebsite("Database", R.drawable.calculator, "Database");
		//=========================21
		//=========================

		createCategoryForWebsite("My Note", R.drawable.note, "NoteArrayList");
		//=========================21
		//=========================
	}

}

