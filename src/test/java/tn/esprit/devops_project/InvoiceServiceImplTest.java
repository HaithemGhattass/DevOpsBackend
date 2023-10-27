package tn.esprit.devops_project;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.InvoiceServiceImpl;

import java.util.ArrayList;

import java.util.List;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;


    @Mock
    private InvoiceRepository invoiceRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void GetAll_ReturnMoreThenOneInvoice() {
        List<Invoice> invoices = new ArrayList<>();
        when(invoiceService.retrieveAllInvoices()).thenReturn(invoices);

        List<Invoice> result = invoiceService.retrieveAllInvoices();

        Assertions.assertThat(result).isNotNull();
    }


}

