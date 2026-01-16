package es.upm.grise.profundizacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class LoanApprovalServiceTest {

    private final LoanApprovalService service = new LoanApprovalService();

    //Camino básico 1:
    @Test
    void caso_camino_basico_1_rejected_por_score_bajo() {
        LoanApprovalService.Applicant applicant =
                new LoanApprovalService.Applicant(3000, 450, false, false);

        LoanApprovalService.Decision result =
                service.evaluateLoan(applicant, 10000, 24);

        assertEquals(LoanApprovalService.Decision.REJECTED, result);
    }

    // Camino básico 2:
    @Test
    void caso_camino_basico_2_manual_review_score_medio_income_ok() {
        LoanApprovalService.Applicant applicant =
                new LoanApprovalService.Applicant(3000, 600, false, false);

        LoanApprovalService.Decision result =
                service.evaluateLoan(applicant, 10000, 24);

        assertEquals(LoanApprovalService.Decision.MANUAL_REVIEW, result);
    }

    // Camino básico 3:
    @Test
    void caso_camino_basico_3_rejected_score_medio_income_bajo() {
        LoanApprovalService.Applicant applicant =
                new LoanApprovalService.Applicant(2000, 600, false, false);

        LoanApprovalService.Decision result =
                service.evaluateLoan(applicant, 10000, 24);

        assertEquals(LoanApprovalService.Decision.REJECTED, result);
    }

    // Camino básico 4:
    @Test
    void caso_camino_basico_4_approved_score_alto_amount_asumible() {
        LoanApprovalService.Applicant applicant =
                new LoanApprovalService.Applicant(3000, 700, false, false);

        LoanApprovalService.Decision result =
                service.evaluateLoan(applicant, 20000, 24);

        assertEquals(LoanApprovalService.Decision.APPROVED, result);
    }

    //Camino básico 5:
    @Test
    void caso_camino_basico_5_manual_review_score_alto_amount_alto() {
        LoanApprovalService.Applicant applicant =
                new LoanApprovalService.Applicant(3000, 700, false, false);

        LoanApprovalService.Decision result =
                service.evaluateLoan(applicant, 30000, 24);

        assertEquals(LoanApprovalService.Decision.MANUAL_REVIEW, result);
    }

    //Camino básico 6:
    @Test
    void caso_camino_basico_6_vip_override_aprueba() {
        LoanApprovalService.Applicant applicant =
                new LoanApprovalService.Applicant(3000, 620, false, true);

        LoanApprovalService.Decision result =
                service.evaluateLoan(applicant, 10000, 24);

        assertEquals(LoanApprovalService.Decision.APPROVED, result);
    }
}
