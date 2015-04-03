package sg.edu.nus.iss.service;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sg.edu.nus.iss.gui.DiscountPanel;
import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.ProductPanel;
import sg.edu.nus.iss.models.Discount;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.util.Constants;

public class DiscountManager implements Constants {
	/*
	 * Created by Bani and Deepsha on 21St March2015 *
	 */

	private ArrayList<Discount> discountList = null;
	private Discount discount;

	public DiscountManager() {
		discountList = new ArrayList<Discount>();
		discount = new Discount();

	}

	public ArrayList<Discount> getDiscountList() {
		try {
			discountList = retrieveDiscountDataFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return discountList;
	}

	public void setDiscountList(ArrayList<Discount> discountList) {

		this.discountList = discountList;
	}

	public ArrayList<Discount> retrieveDiscountDataFromFile()
			throws IOException {
		String dataofFile = null;
		Discount discount = null;
		ArrayList<Discount> discountList = new ArrayList<Discount>();
		;
		FileReader r = null;
		BufferedReader br = null;
		try {
			r = new FileReader(Constants.DISCOUNTFILE);
			br = new BufferedReader(r);
			while ((dataofFile = br.readLine()) != null) {
				System.out.println(dataofFile);

				List<String> discountString = Arrays.asList(dataofFile
						.split(","));
				for (int z = 0; z <= discountString.size(); z++) {

					discount = new Discount();
					discount.setDiscountCode(discountString.get(0));
					discount.setDiscountDescription(discountString.get(1));
					discount.setStartDate(discountString.get(2));
					discount.setDurationOfDiscount(discountString.get(3));
					discount.setDiscountPercentage(discountString.get(4));
					discount.setApplicability(discountString.get(5));
					discountList.add(discount);
					break;
				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch b

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br.close();
		}

		return discountList;
	}

	public void displayDiscountFile(ArrayList discountList) {
		Iterator<Discount> iterator = discountList.iterator();
		while (iterator.hasNext()) {
			Discount d = iterator.next();
			System.out.println("Code:" + d.getDiscountCode());
			System.out.println("Description:" + d.getDiscountDescription());
			System.out.println("Start Date:" + d.getStartDate());
			System.out.println("Duration Of Discount:"
					+ d.getDurationOfDiscount());
			System.out.println("Discount Percentage:"
					+ d.getDiscountPercentage());
			System.out
					.println("Discount Applicability:" + d.getApplicability());

		}
	}

	public boolean addNewDiscountData(Discount discount) throws IOException {

		if (discount != null) {

			String content = discount.getDiscountCode() + ","
					+ discount.getDiscountDescription() + ","
					+ discount.getStartDate() + ","
					+ discount.getDurationOfDiscount() + ","
					+ discount.getDiscountPercentage() + ","
					+ discount.getApplicability();
			System.out.println(content);

			BufferedWriter bout = new BufferedWriter(new FileWriter(
					Constants.DISCOUNTFILE, true));

			bout.append(content);
			bout.newLine();
			bout.close();
		}
		return true;
	}

	public ArrayList<Discount> deleteDiscountData(Discount discount)
			throws IOException {
		ArrayList<Discount> discountList = null;
		if (discount != null) {

			String content = discount.getDiscountCode() + ","
					+ discount.getDiscountDescription() + ","
					+ discount.getStartDate() + ","
					+ discount.getDurationOfDiscount() + ","
					+ discount.getDiscountPercentage() + ","
					+ discount.getApplicability();
			System.out.println("to be deleted::" + content);

			discountList = retrieveDiscountDataFromFile();

			System.out.println("before" + discountList.size());

			// displayDiscountFile(discountList);
			BufferedWriter bout = new BufferedWriter(new FileWriter(
					Constants.DISCOUNTFILE));
			Iterator<Discount> iterator = discountList.iterator();
			while (iterator.hasNext()) {

				Discount dis = iterator.next();
				if (dis.getDiscountCode().equalsIgnoreCase(
						discount.getDiscountCode())) {
					System.out.println("Removed");
					discountList.remove(dis);
					break;
				} else {
					System.out.println("Data not found");

				}
				String line = dis.getDiscountCode() + ","
						+ dis.getDiscountDescription() + ","
						+ dis.getStartDate() + ","
						+ dis.getDurationOfDiscount() + ","
						+ dis.getDiscountPercentage() + ","
						+ dis.getApplicability();
				System.out.println(line);
				bout.write(line);
				bout.newLine();

			}
			System.out.println("after deletion:" + discountList.size());
			bout.close();
		}
		return discountList;
	}

	public void modifyDiscountData(Discount discount1) throws IOException {
		System.out.print("Modify");
		deleteDiscountData(discount1);
		// 1
		// addNewDiscountData(discount1)

	}

	public ArrayList<Discount> searchDataAndDisplay(String data, String value) {
		ArrayList<Discount> searchedlist = new ArrayList<Discount>();
		ArrayList<Discount> discountList = null;
		Discount searcheddiscount;
		System.out.println("searchDataAndDisplay" + value + data);
		try {
			discountList = retrieveDiscountDataFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Discount> iterator = discountList.iterator();
		while (iterator.hasNext()) {
			Discount dis = iterator.next();
			if (data.equalsIgnoreCase("Discount Code")) {
				if (value.equalsIgnoreCase(dis.getDiscountCode())) {
					searcheddiscount = new Discount();
					searcheddiscount = dis;

					searchedlist.add(searcheddiscount);

					System.out.println("matched DiscountCode");
				}
			} else if (data.equalsIgnoreCase("Applicability")) {
				if (value.equalsIgnoreCase(dis.getApplicability())) {

					searcheddiscount = new Discount();
					searcheddiscount = dis;
					searchedlist.add(searcheddiscount);
				}
			} else if (data.equalsIgnoreCase("Discount Description")) {

				if (dis.getDiscountDescription().contains(value)) {
					searcheddiscount = new Discount();
					searcheddiscount = dis;
					searchedlist.add(searcheddiscount);

					System.out.println("matched Description");
				}
			}

		}
		System.out.println("searchedlist:" + searchedlist.size());
		return searchedlist;
	}

	public String getDiscountApplicable(String memberID) throws ParseException,
			IOException {
		// TODO Auto-generated method stub
		String discountOffered = null;
		// int maxDiscount=0;

		ArrayList<Integer> discountPerctList = null;
		ArrayList discountList = retrieveDiscountDataFromFile();
		Iterator<Discount> iterator = discountList.iterator();
		while (iterator.hasNext()) {
			Discount discount = iterator.next();

			if (memberID.equalsIgnoreCase("Public")) {
				System.out.println("Public");
				discountPerctList = new ArrayList<Integer>();
				if (discount.getApplicability().equalsIgnoreCase("A")) {
					System.out.println("Applicable to All");
					String startDate = discount.getStartDate();
					String duration = discount.getDurationOfDiscount();
					if (!startDate.equalsIgnoreCase("Always")
							|| (!duration.equalsIgnoreCase("Always"))) {
						Date now = new Date();
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date startdateofDiscount = format.parse(startDate);

						if (startdateofDiscount.before(now)) {
							System.out.println("startdateofDiscount is valid");

							int differnece = getDifferenceDays(
									startdateofDiscount, now);
							int days = Integer.parseInt(duration);
							if (differnece <= days) {

								discountOffered = discount
										.getDiscountPercentage();

								int maxDiscount = Integer
										.parseInt(discountOffered);
								discountPerctList.add(maxDiscount);

							}

						}

					} else {
						System.out.println("Alwys applicable");
						discountOffered = discount.getDiscountPercentage();

						int maxDiscount = Integer.parseInt(discountOffered);
						discountPerctList.add(maxDiscount);
					}

					System.out.println("discountPerctList"
							+ discountPerctList.size());
					int i = Collections.max(discountPerctList);
					System.out.println("Max discount value" + i);
					discountOffered = "" + i;

				}

			} else {
				discountPerctList = new ArrayList<Integer>();
				if (discount.getDiscountCode().contains("First")) {
					// First time member
					System.out.println("First Time member");
					discountOffered = discount.getDiscountPercentage();
					break;
				} else {
					String startDate = discount.getStartDate();
					String duration = discount.getDurationOfDiscount();
					if (!startDate.equalsIgnoreCase("Always")
							|| (!duration.equalsIgnoreCase("Always"))) {
						Date now = new Date();
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date startdateofDiscount = format.parse(startDate);

						if (startdateofDiscount.before(now)) {
							System.out.println("startdateofDiscount is valid");

							int differnece = getDifferenceDays(
									startdateofDiscount, now);
							int days = Integer.parseInt(duration);
							if (differnece <= days) {

								discountOffered = discount
										.getDiscountPercentage();

								int maxDiscount = Integer
										.parseInt(discountOffered);
								discountPerctList.add(maxDiscount);

							}

						}

					} else {
						System.out.println("Alwys applicable");
						discountOffered = discount.getDiscountPercentage();

						int maxDiscount = Integer.parseInt(discountOffered);
						discountPerctList.add(maxDiscount);
					}

					System.out.println("discountPerctList"
							+ discountPerctList.size());
					int i = Collections.max(discountPerctList);
					System.out.println("Max discount value" + i);
					discountOffered = "" + i;

				}

			}

		}

		return discountOffered;
	}

	public int getDifferenceDays(Date d1, Date d2) {
		int daysdiff = 0;
		long diff = d2.getTime() - d1.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		daysdiff = (int) diffDays;
		return daysdiff;
	}

	public boolean writeBackToFile(ArrayList discountList) throws IOException {

		if (discountList != null) {

			BufferedWriter bout = new BufferedWriter(new FileWriter(
					Constants.DISCOUNTFILE));
			Iterator<Discount> iterator = discountList.iterator();
			while (iterator.hasNext()) {

				Discount dis = iterator.next();

				String line = dis.getDiscountCode() + ","
						+ dis.getDiscountDescription() + ","
						+ dis.getStartDate() + ","
						+ dis.getDurationOfDiscount() + ","
						+ dis.getDiscountPercentage() + ","
						+ dis.getApplicability();
				System.out.println(line);
				bout.write(line);
				bout.newLine();

			}

			bout.close();
		}
		return true;
	}
	public Discount getDiscount(){
		return discount;
	}
}
