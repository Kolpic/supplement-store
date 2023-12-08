package org.example.helper;

import lombok.Getter;
import org.example.model.BaseModel;
import org.example.model.aminoasid.BCAA;
import org.example.model.aminoasid.BaseAminoAcid;
import org.example.model.aminoasid.EAA;
import org.example.model.aminoasid.LCarnitine;
import org.example.model.protein.BaseProtein;
import org.example.model.protein.ProteinIsolate;
import org.example.model.protein.VeganProtein;
import org.example.model.protein.WheyProtein;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Getter
public class FileReaderSupplements {

    private Map<String, List<BaseProtein>> proteinz = new HashMap<>();
    private Map<String, List<BaseAminoAcid>> aminos = new HashMap<>();
    private Map<String, List<BaseModel>> baseModel = new LinkedHashMap<>();

    // TODO: Да махна листовете и да оптимизирам добавянето
    public void readSupplementsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\db\\" + fileName))) {
            String line;
            BaseProtein currentProtein = null;
            BaseAminoAcid currentAminoAcid = null;
            String categoryName = "";
            String subCategory = "";

            while ((line = br.readLine()) != null) {
                // Check if the line starts a new category
                if (line.startsWith("<")) {
                    categoryName = line.substring(1, line.length() - 1);
                } else if (line.startsWith("    _")) {
                    subCategory = line.substring(5);
                } else if (line.startsWith("        *")) {
                    String[] parts = line.substring(9).split(",");
                    String productName = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    String imagePath = parts[2];

                    ImageIcon imageIcon = new ImageIcon("src\\main\\resources\\" + imagePath);

                    if (subCategory.equals("whey protein") && categoryName.equals("proteins")) {
                        currentProtein = new WheyProtein(productName, price, imageIcon);
                        if (baseModel.get(subCategory) == null) {
                            baseModel.put(subCategory, new ArrayList<>());
                            baseModel.get(subCategory).add(currentProtein);
                        } else {
                            baseModel.get(subCategory).add(currentProtein);
                        }
                        //////////////////////////////////////////////////
                        if (proteinz.get(subCategory) == null) {
                            proteinz.put(subCategory, new ArrayList<>());
                            proteinz.get(subCategory).add(currentProtein);
                        } else {
                            proteinz.get(subCategory).add(currentProtein);
                        }
                    } else if (subCategory.equals("protein isolate") && categoryName.equals("proteins")) {
                        currentProtein = new ProteinIsolate(productName, price, imageIcon);
                        if (baseModel.get(subCategory) == null) {
                            baseModel.put(subCategory, new ArrayList<>());
                            baseModel.get(subCategory).add(currentProtein);
                        } else {
                            baseModel.get(subCategory).add(currentProtein);
                        }
                        //////////////////////////////////////////////////
                        if (proteinz.get(subCategory) == null) {
                            proteinz.put(subCategory, new ArrayList<>());
                            proteinz.get(subCategory).add(currentProtein);
                        } else {
                            proteinz.get(subCategory).add(currentProtein);
                        }
                    } else if (subCategory.equals("vegan protein") && categoryName.equals("proteins")) {
                        currentProtein = new VeganProtein(productName, price, imageIcon);
                        if (baseModel.get(subCategory) == null) {
                            baseModel.put(subCategory, new ArrayList<>());
                            baseModel.get(subCategory).add(currentProtein);
                        } else {
                            baseModel.get(subCategory).add(currentProtein);
                        }
                        //////////////////////////////////////////////////
                        if (proteinz.get(subCategory) == null) {
                            proteinz.put(subCategory, new ArrayList<>());
                            proteinz.get(subCategory).add(currentProtein);
                        } else {
                            proteinz.get(subCategory).add(currentProtein);
                        }
                    } else if (subCategory.equals("BCAA supplements") && categoryName.equals("amino acids")) {
                        currentAminoAcid = new BCAA(productName, price, imageIcon);
                        if (baseModel.get(subCategory) == null) {
                            baseModel.put(subCategory, new ArrayList<>());
                            baseModel.get(subCategory).add(currentAminoAcid);
                        } else {
                            baseModel.get(subCategory).add(currentAminoAcid);
                        }
                        //////////////////////////////////////////////////
                        if (aminos.get(subCategory) == null) {
                            aminos.put(subCategory, new ArrayList<>());
                            aminos.get(subCategory).add(currentAminoAcid);
                        } else {
                            aminos.get(subCategory).add(currentAminoAcid);
                        }
                    } else if (subCategory.equals("EAA supplements") && categoryName.equals("amino acids")) {
                        currentAminoAcid = new EAA(productName, price, imageIcon);
                        if (baseModel.get(subCategory) == null) {
                            baseModel.put(subCategory, new ArrayList<>());
                            baseModel.get(subCategory).add(currentAminoAcid);
                        } else {
                            baseModel.get(subCategory).add(currentAminoAcid);
                        }
                        //////////////////////////////////////////////////
                        if (aminos.get(subCategory) == null) {
                            aminos.put(subCategory, new ArrayList<>());
                            aminos.get(subCategory).add(currentAminoAcid);
                        } else {
                            aminos.get(subCategory).add(currentAminoAcid);
                        }
                    } else if (subCategory.equals("L-Carnitine supplements") && categoryName.equals("amino acids")) {
                        currentAminoAcid = new LCarnitine(productName, price, imageIcon);
                        if (baseModel.get(subCategory) == null) {
                            baseModel.put(subCategory, new ArrayList<>());
                            baseModel.get(subCategory).add(currentAminoAcid);
                        } else {
                            baseModel.get(subCategory).add(currentAminoAcid);
                        }
                        //////////////////////////////////////////////////
                        if (aminos.get(subCategory) == null) {
                            aminos.put(subCategory, new ArrayList<>());
                            aminos.get(subCategory).add(currentAminoAcid);
                        } else {
                            aminos.get(subCategory).add(currentAminoAcid);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
