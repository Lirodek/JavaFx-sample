package org.sight.kiosk.util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.Attribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import java.awt.print.PrinterJob;
import java.io.File;
import java.net.URL;

public class PDFPrinter {

    private static PDFPrinter instance;

    // private 생성자로 외부에서 객체 생성 방지
    private PDFPrinter() {}

    // 인스턴스 반환 메서드
    public static synchronized PDFPrinter getInstance() {
        if (instance == null) {
            instance = new PDFPrinter();
        }
        return instance;
    }

    public String print(String pdfFileName) {

        if(pdfFileName == null || pdfFileName.trim().equals("")) {
            throw new RuntimeException( "오류가 발생했습니다.\n 파일명을 입력해주세요." );
        }

        try {
            // 2. PDF 문서를 로드
            pdfFileName = "files/" + pdfFileName + ".pdf";
            URL pdfFileUrl = getClass().getClassLoader().getResource(pdfFileName);
            if (pdfFileUrl == null) {
                throw new RuntimeException("PDF 파일을 찾을 수 없습니다: " + pdfFileName);
            }

            // URL을 File로 변환
            File pdfFile = new File(pdfFileUrl.toURI());
            System.out.println("PDF 파일 경로: " + pdfFile.getAbsolutePath());

            System.out.println(pdfFile);
            PDDocument document = PDDocument.load(pdfFile);

            // 3. 프린터 작업 생성
            PrinterJob printerJob = PrinterJob.getPrinterJob();

            // 4. 사용 가능한 프린터 확인 및 설정
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            if (printServices.length > 0) {
                System.out.println("사용 가능한 프린터 목록:");
                for (int i = 0; i < printServices.length; i++) {
                    System.out.println((i + 1) + ": " + printServices[i].getName());
                    // 프린터 속성 가져오기
                    PrintServiceAttributeSet attributes = printServices[i].getAttributes();
                    for (Attribute attr : attributes.toArray()) {
                        System.out.println("  " + attr.getName() + ": " + attr.toString());
                    }

                    // 프린터 상태 확인
                    PrinterState state = (PrinterState) attributes.get(PrinterState.class);
                    if (state != null) {
                        System.out.println("  상태: " + state);
                    }

                    // 추가 상태 이유 확인
                    PrinterStateReason reason = (PrinterStateReason) attributes.get(PrinterStateReason.class);
                    if (reason != null) {
                        System.out.println("  상태 이유: " + reason);
                    }

                    // 작업 수락 여부 확인
                    PrinterIsAcceptingJobs acceptingJobs = (PrinterIsAcceptingJobs) attributes.get(PrinterIsAcceptingJobs.class);
                    if (acceptingJobs != null) {
                        System.out.println("  작업 수락 여부: " + acceptingJobs);
                    }
                }
                // 첫 번째 프린터로 설정
                printerJob.setPrintService(printServices[0]); // 필요에 따라 다른 인덱스 선택
            } else {
                throw new RuntimeException("사용 가능한 프린터가 없습니다.");
            }

            // 5. PDF를 페이지 단위로 추가
            printerJob.setPageable(new PDFPageable(document));

            // 6. 인쇄 시작
            printerJob.print();

            // 7. 문서 닫기
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("PDF 인쇄 중 오류 발생: " + e.getMessage());
        }

        return "PDF가 성공적으로 프린터로 전송되었습니다!";
    }
}
