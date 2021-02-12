package com.gmail.psyh2409.june_java_job;

import com.gmail.psyh2409.june_java_job.model.Company;
import com.gmail.psyh2409.june_java_job.model.ContactPerson;
import com.gmail.psyh2409.june_java_job.model.URLOfCompany;
import com.gmail.psyh2409.june_java_job.model.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<Vacancy> getVacancies(String surl) {
        surl = "https://djinni.co/jobs2/?category=java&location=kyiv&experience=1";

        List<String> surls = djinniParser(surl);
        List<Vacancy> vacancies = new ArrayList<>();

        surls.forEach(z -> {

        Vacancy vacancy = new Vacancy();
        vacancy.setVacancyName(getVacancyName(z));
        String pref = "https://djinni.co";
        Company company = new Company();
        company.getCompanyURLs().add(addURLofVacanciesOfCompanyToList(z, pref));
        company.setCompanyName(getCompanyName(z));
        company.setAboutCompany(getStringAboutCompany(z));
        vacancy.setCompany(company);
        vacancy.setContactPerson(getContactPersonAndItsName(z));
        vacancy.setTextOfVacancy(z);
//        vacancy.setPublication(new LocalDate().now.getYear().plus(4));
            vacancies.add(vacancy);
        });
        return vacancies;
    }

    private static ContactPerson getContactPersonAndItsName(String surls) {
        ContactPerson contactPerson = new ContactPerson();

        String cp = docFromStrURL(surls).body()
                .child(0)
                .child(1)
                .child(3)
                .child(1)
                .child(0)
                .child(1)
                .child(0)
                .text();
        contactPerson.setContactPersonName(cp);
        contactPerson.setContactPerson(docFromStrURL(surls).body()
                .child(0)
                .child(1)
                .child(3)
                .child(1)
                .child(0)
                .child(1)
                .text()
                .replace(contactPerson.getContactPersonName(), "")
                .trim());
        return contactPerson;
    }

    private static String getStringAboutCompany(String surls) {
        return docFromStrURL(
                surls)
                .body()
                .child(0)
                .child(1)
                .child(3)
                .child(1)
                .child(0)
                .child(1)
                .getAllElements()
                .text();
    }

    private static String getCompanyName(String surls) {
        return getChildA(surls).text();
    }

    private static URLOfCompany addURLofVacanciesOfCompanyToList(String surls, String pref) {
        URLOfCompany uRLOfCompany = new URLOfCompany();
        try {
            uRLOfCompany.setUrl(new URL(pref.concat(getChildA(surls)
                            .getAllElements()
                            .attr("href"))));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return uRLOfCompany;
    }

    private static Element getChildA(String surls) {
        return docFromStrURL(surls)
                .body()
                .child(0)
                .child(1)
                .child(1)
                .child(2)
                .child(0);
    }

    private static String getVacancyName(String surl) {
        return docFromStrURL(
                surl)
                .body()
                .child(0)
                .child(1)
                .child(1)
                .child(1)
                .text();
    }

    private static List<String> djinniParser(String surl) {
        String prefix = "https://djinni.co";
        List<String> listSURL = new ArrayList<>();
        docFromStrURL(surl)
                .body()
                .child(0)
                .child(1)
                .child(0)
                .child(1)
                .children().forEach(x -> {
            listSURL.add(
                    prefix.concat(x
                            .child(1)
                            .child(0)
                            .child(0)
                            .toString()
                            .replaceAll("<a rel=\"prefetch\" href=\"", "")
                            .split("\"")[0]
                    ));
        });
        return listSURL;
    }

    private static String textOfVacancy(String surl) {
        String textWithFinalTag =
                docFromStrURL(surl)
                        .body()
                        .child(0)
                        .child(1)
                        .child(3)
                        .child(0)
                        .child(1)
                        .toString()
                        .substring(25);
        return textWithFinalTag.substring(0, textWithFinalTag.length() - 4);
    }

    private static Document docFromStrURL(String surl) {
        try {
            return Jsoup.parse(new URL(surl), 10000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
