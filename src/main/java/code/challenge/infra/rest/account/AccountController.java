package code.challenge.infra.rest.account;

import code.challenge.app.exceptions.account.AccountNotFoundException;
import code.challenge.app.exceptions.account.ErrorCreatingAccountException;
import code.challenge.app.usecases.account.CreateAccountByCustomerCpfAccountAndAgencyUseCase;
import code.challenge.app.usecases.account.GetAccountUseCase;
import code.challenge.app.usecases.account.InactivateAccountUseCase;
import code.challenge.infra.dtos.CreateAccountDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {
    private final GetAccountUseCase getAccountUseCase;
    private final CreateAccountByCustomerCpfAccountAndAgencyUseCase createAccountByCustomerCpfAccountAndAgencyUseCase;
    private final InactivateAccountUseCase inactivateAccountUseCase;

    public AccountController(
            GetAccountUseCase getAccountUseCase,
            CreateAccountByCustomerCpfAccountAndAgencyUseCase createAccountByCustomerCpfAccountAndAgencyUseCase,
            InactivateAccountUseCase inactivateAccountUseCase
    ) {
        this.getAccountUseCase = getAccountUseCase;
        this.createAccountByCustomerCpfAccountAndAgencyUseCase = createAccountByCustomerCpfAccountAndAgencyUseCase;
        this.inactivateAccountUseCase = inactivateAccountUseCase;
    }

    @GET
    @Path("/{cpf}")
    public Response getAccount(
            @PathParam("cpf") String cpf
    ) {
        try {
            var useCaseInput = new GetAccountUseCase.Input(cpf);
            var account = getAccountUseCase.execute(useCaseInput);

            return Response.ok(account).build();
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/create")
    @Transactional
    public Response createAccount(
            @Valid CreateAccountDTO createAccountDTO
    ) {
        try {
            var useCaseInput = new CreateAccountByCustomerCpfAccountAndAgencyUseCase.Input(
                    createAccountDTO.customerCpf(),
                    createAccountDTO.accountNumber(),
                    createAccountDTO.agency()
            );

            var account = createAccountByCustomerCpfAccountAndAgencyUseCase.execute(useCaseInput);

            return Response.ok(account).build();
        } catch (ErrorCreatingAccountException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PATCH
    @Path("/inactivate/{accountNumber}")
    @Transactional
    public Response inactivateAccount(
            @PathParam("accountNumber") String accountNumber
    ) {
        try {
            var useCaseInput = new InactivateAccountUseCase.Input(accountNumber);
            var account = inactivateAccountUseCase.execute(useCaseInput);

            return Response.ok(account).build();
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
