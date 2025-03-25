import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MonolithicBankingSystem {
    protected Map<String, Bank> banks = new HashMap<>();
    protected DecimalFormat df = new DecimalFormat("#.##");
    private static final Scanner scanner = new Scanner(System.in);
    private static final String LOG_FILE = "banking_logs.txt";
    private static final String TRANSACTION_FILE = "transactions.csv";

    public MonolithicBankingSystem() {
        initializeBanks();
    }

    private void initializeBanks() {
        List<BankService> commonServices = Arrays.asList(
            new BankService("Savings Account", "Earn interest on deposits", 2.5),
            new BankService("Checking Account", "Daily transactions", 0.1),
            new BankService("Loans", "Personal and business financing", 6.0),
            new BankService("Credit Cards", "Credit line with rewards", 15.0),
            new BankService("Wealth Management", "Investment advisory", 1.0),
            new BankService("Mortgage Lending", "Home financing", 4.5),
            new BankService("Insurance Products", "Risk protection", 3.0),
            new BankService("Commercial Banking", "Business financing", 5.0)
        );

        String[] countries = {
            "Argentina", "Canada", "Europe", "Germany", "Africa", "China",
            "France", "Mexico", "India", "North America", "Japan", "South America"
        };

        Map<String, String> countryCurrencies = new HashMap<>();
        countryCurrencies.put("Argentina", "ARS");
        countryCurrencies.put("Canada", "CAD");
        countryCurrencies.put("Europe", "EUR");
        countryCurrencies.put("Germany", "EUR");
        countryCurrencies.put("Africa", "ZAR"); 
        countryCurrencies.put("China", "CNY");
        countryCurrencies.put("France", "EUR");
        countryCurrencies.put("Mexico", "MXN");
        countryCurrencies.put("India", "INR");
        countryCurrencies.put("North America", "USD");
        countryCurrencies.put("Japan", "JPY");
        countryCurrencies.put("South America", "BRL"); 

        for (String country : countries) {
            for (int i = 1; i <= 20; i++) {
                String bankName = getBankName(country, i);
                String currency = countryCurrencies.get(country);
                Bank bank = new Bank(bankName, country, currency, "Commercial", null);
                bank.services = new ArrayList<>(commonServices); 
                banks.put(bankName, bank);
            }
        }
    }

    private String getBankName(String country, int i) {
        String[] banks;
        switch (country) {
            case "Argentina":
                banks = new String[]{"Banco de la Nación Argentina", "Banco Galicia", "Banco Macro", "Banco Santander Río",
                    "Banco Provincia", "BBVA Banco Francés", "Banco Ciudad", "HSBC Argentina", "Banco Patagonia",
                    "Banco Credicoop", "Banco Supervielle", "Banco Comafi", "Banco de Córdoba", "Banco Hipotecario",
                    "Banco del Chubut", "Banco del Sol", "Banco del Tucumán", "Banco de Formosa", "Banco de San Juan",
                    "Banco de Santa Fe"};
                break;
            case "Canada":
                banks = new String[]{"Royal Bank of Canada", "Toronto-Dominion Bank", "Bank of Nova Scotia", "Bank of Montreal",
                    "Canadian Imperial Bank", "National Bank of Canada", "HSBC Bank Canada", "Laurentian Bank",
                    "Canadian Western Bank", "Equitable Bank", "Tangerine Bank", "Simplii Financial", "Manulife Bank",
                    "President's Choice Financial", "Home Trust Company", "Bridgewater Bank", "First Nations Bank",
                    "Concentra Bank", "Alterna Bank", "Motus Bank"};
                break;
            case "Europe":
                banks = new String[]{"HSBC UK", "Barclays", "Lloyds Bank", "NatWest Group", "Santander UK", "Standard Chartered",
                    "The Co-operative Bank", "TSB Bank", "Metro Bank", "Yorkshire Bank", "Clydesdale Bank", "Monzo",
                    "Revolut", "Starling Bank", "Virgin Money UK", "Tesco Bank", "Aldermore Bank", "Bank of Scotland",
                    "Handelsbanken UK", "Shawbrook Bank"};
                break;
            case "Germany":
                banks = new String[]{"Deutsche Bank", "Commerzbank", "KfW Bankengruppe", "DZ Bank", "UniCredit Bank",
                    "Landesbank Baden-Württemberg", "Norddeutsche Landesbank", "BayernLB", "Helaba", "ING-DiBa",
                    "Postbank", "Hamburg Commercial Bank", "Aareal Bank", "Berlin Hyp", "DekaBank", "Oldenburgische Landesbank",
                    "Triodos Bank Germany", "Targobank", "Quirin Privatbank", "GLS Bank"};
                break;
            case "Africa":
                banks = new String[]{"Standard Bank", "First National Bank", "Absa Group", "Nedbank", "Capitec Bank",
                    "Investec Bank", "Bidvest Bank", "African Bank", "TymeBank", "Sasfin Bank", "Ubank", "Mercantile Bank",
                    "HBZ Bank", "Bank Zero", "Grobank", "Finbond Mutual Bank", "Postbank South Africa", "Rand Merchant Bank",
                    "Al Baraka Bank", "Discovery Bank"};
                break;
            case "China":
                banks = new String[]{"Industrial and Commercial Bank", "China Construction Bank", "Agricultural Bank of China",
                    "Bank of China", "China Merchants Bank", "Bank of Communications", "Postal Savings Bank", "Shanghai Pudong Development Bank",
                    "China CITIC Bank", "Huaxia Bank", "China Minsheng Banking", "China Everbright Bank", "Industrial Bank Co.",
                    "Ping An Bank", "Bank of Beijing", "Bank of Shanghai", "Bank of Jiangsu", "Bank of Nanjing", "Bank of Hangzhou",
                    "Chongqing Rural Commercial Bank"};
                break;
            case "France":
                banks = new String[]{"BNP Paribas", "Société Générale", "Crédit Agricole", "Crédit Mutuel", "La Banque Postale",
                    "Banque Populaire", "Caisse d’Épargne", "LCL", "HSBC France", "AXA Banque", "Crédit du Nord", "Boursorama Banque",
                    "Banque Palatine", "Banque de Savoie", "Banque Wormser Frères", "Banque Chabrières", "Banque Neuflize OBC",
                    "Banque Rothschild", "Banque Courtois", "Banque Kolb"};
                break;
            case "Mexico":
                banks = new String[]{"BBVA México", "Banamex", "Santander México", "Banorte", "HSBC México", "Scotiabank Inverlat",
                    "Banco Inbursa", "Banco Azteca", "Banco del Bajío", "Banco Interacciones", "Banco Ve por Más", "Banco Multiva",
                    "Banco Famsa", "Banco Autofin México", "Banco Ahorro Famsa", "Banco Compartamos", "Banco Actinver", "Banco Invex",
                    "Banco Bancrea", "Banco Forjadores"};
                break;
            case "India":
                banks = new String[]{"State Bank of India", "HDFC Bank", "ICICI Bank", "Axis Bank", "Punjab National Bank",
                    "Bank of Baroda", "Canara Bank", "Union Bank of India", "Kotak Mahindra Bank", "IndusInd Bank", "IDBI Bank",
                    "Indian Bank", "Bank of India", "Federal Bank", "YES Bank", "RBL Bank", "South Indian Bank", "Karur Vysya Bank",
                    "Tamilnad Mercantile Bank", "Ujjivan Small Finance Bank"};
                break;
            case "North America":
                banks = new String[]{"JPMorgan Chase & Co.", "Bank of America", "Wells Fargo", "Citigroup", "Goldman Sachs",
                    "Morgan Stanley", "U.S. Bancorp", "Truist Financial", "PNC Financial Services", "TD Bank", "Capital One",
                    "Fifth Third Bank", "Citizens Financial Group", "KeyBank", "Regions Financial", "M&T Bank", "Huntington Bancshares",
                    "First Republic Bank", "Comerica", "Silicon Valley Bank"};
                break;
            case "Japan":
                banks = new String[]{"Mitsubishi UFJ Financial Group", "Sumitomo Mitsui Banking", "Mizuho Financial Group",
                    "Resona Holdings", "Shinsei Bank", "Japan Post Bank", "Aozora Bank", "Chiba Bank", "Fukuoka Financial Group",
                    "Hokuhoku Financial Group", "Nishi-Nippon City Bank", "Seven Bank", "Shizuoka Bank", "Toho Bank",
                    "Yamaguchi Financial Group", "Bank of Kyoto", "Sony Bank", "Rakuten Bank", "SBI Sumishin Net Bank",
                    "Bank of Yokohama"};
                break;
            case "South America":
                banks = new String[]{"Banco do Brasil", "Itaú Unibanco", "Bradesco", "Caixa Econômica Federal", "Santander Brasil",
                    "Banco Safra", "BTG Pactual", "Banco Votorantim", "Banco Pan", "Banco Inter", "Banco Original", "Banco ABC Brasil",
                    "Banco Daycoval", "Banco Modal", "Banco BMG", "Banco Fibra", "Banco Mercantil do Brasil", "Banco Pine",
                    "Banco Industrial do Brasil", "Banco Topázio"};
                break;
            default:
                banks = new String[]{"Bank " + i};
        }
        return banks[i - 1];
    }

    static class BankService {
        String name;
        String functionality;
        double rate;

        BankService(String name, String functionality, double rate) {
            this.name = name;
            this.functionality = functionality;
            this.rate = rate;
        }

        @Override
        public String toString() {
            return String.format("%s (Functionality: %s, Rate: %.2f%%)", name, functionality, rate);
        }
    }

    public class Bank {
        public String bankName, country, currency, type, specialization;
        public double baseInterestRate, moneySupply;
        public Map<String, Customer> customers = new HashMap<>();
        public Map<String, Account> accounts = new HashMap<>();
        public Map<String, Loan> loans = new HashMap<>();
        public Map<String, SectorLoan> sectorLoans = new HashMap<>();
        public Map<String, MicrofinanceLoan> microLoans = new HashMap<>();
        public Map<String, Card> cards = new HashMap<>();
        public Map<String, Insurance> insurances = new HashMap<>();
        public Map<String, Investment> investments = new HashMap<>();
        public Map<String, Locker> lockers = new HashMap<>();
        public Map<String, CommercialBank> supervisedBanks = new HashMap<>();
        public Map<String, Double> exchangeRates = new HashMap<>();
        public Map<String, Double> foreignExchangeReserves = new HashMap<>();
        public Map<String, Transaction> transactions = new HashMap<>();
        public List<Transaction> liquidityOperations = new ArrayList<>();
        public List<BankService> services = new ArrayList<>();

        public Bank(String bankName, String country, String currency, String type, String specialization) {
            this.bankName = bankName;
            this.country = country;
            this.currency = currency;
            this.type = type;
            this.specialization = specialization;
            this.baseInterestRate = type.equals("Central") ? 4.0 : 0.0;
            this.moneySupply = type.equals("Central") ? 1000000 : 0.0;
            initializeExchangeRates();
            if (type.equals("Central")) initializeReserves();
        }

        private void initializeExchangeRates() {
            exchangeRates.put("INR", 1.0);
            exchangeRates.put("USD", 0.01146);
            exchangeRates.put("EUR", 0.01246);
            exchangeRates.put("GBP", 0.01469);
            exchangeRates.put("CNY", 0.08137);
            exchangeRates.put("JPY", 1.63883);
            exchangeRates.put("DZD", 1.53563);
            exchangeRates.put("AUD", 0.01719);
            exchangeRates.put("THB", 0.37819);
            exchangeRates.put("KRW", 14.89762);
            exchangeRates.put("PKR", 3.18606);
            exchangeRates.put("IDR", 177.61989);
            exchangeRates.put("AED", 0.04208);
            exchangeRates.put("AFN", 0.76628);
            exchangeRates.put("ALL", 1.07912);
            exchangeRates.put("AMD", 4.61454);
            exchangeRates.put("ANG", 0.02066);
            exchangeRates.put("AOA", 9.58333);
            exchangeRates.put("ARS", 11.16562);
            exchangeRates.put("AWG", 0.02066);
            exchangeRates.put("AZN", 0.01949);
            exchangeRates.put("BAM", 0.02435);
            exchangeRates.put("BBD", 0.02292);
            exchangeRates.put("BDT", 1.37174);
            exchangeRates.put("BGN", 0.02435);
            exchangeRates.put("BHD", 0.00432);
            exchangeRates.put("BIF", 32.92777);
            exchangeRates.put("BMD", 0.01146);
            exchangeRates.put("BND", 0.01529);
            exchangeRates.put("BOB", 0.07925);
            exchangeRates.put("BRL", 0.06447);
            exchangeRates.put("BSD", 0.01146);
            exchangeRates.put("BTN", 1.0);
            exchangeRates.put("BWP", 0.15425);
            exchangeRates.put("BYN", 0.03749);
            exchangeRates.put("BZD", 0.02307);
            exchangeRates.put("CAD", 0.01590);
            exchangeRates.put("CDF", 31.91776);
            exchangeRates.put("CHF", 0.01001);
            exchangeRates.put("CLP", 10.98411);
            exchangeRates.put("COP", 48.76562);
            exchangeRates.put("CRC", 5.85822);
            exchangeRates.put("CUP", 0.27485);
            exchangeRates.put("CVE", 1.37288);
            exchangeRates.put("CZK", 0.26305);
            exchangeRates.put("DJF", 2.04392);
            exchangeRates.put("DKK", 0.09282);
            exchangeRates.put("DOP", 0.68309);
            exchangeRates.put("EGP", 0.55756);
            exchangeRates.put("ERN", 0.17191);
            exchangeRates.put("ETB", 1.29773);
            exchangeRates.put("FJD", 0.02573);
            exchangeRates.put("FKP", 0.01469);
            exchangeRates.put("FOK", 0.09282);
            exchangeRates.put("GEL", 0.03044);
            exchangeRates.put("GGP", 0.01469);
            exchangeRates.put("GHS", 0.16638);
            exchangeRates.put("GIP", 0.01469);
            exchangeRates.put("GMD", 0.78198);
            exchangeRates.put("GNF", 98.71119);
            exchangeRates.put("GTQ", 0.08916);
            exchangeRates.put("GYD", 2.39954);
            exchangeRates.put("HKD", 0.08939);
            exchangeRates.put("HNL", 0.28476);
            exchangeRates.put("HRK", 0.09374);
            exchangeRates.put("HTG", 1.51874);
            exchangeRates.put("HUF", 4.19725);
            exchangeRates.put("ILS", 0.04289);
            exchangeRates.put("IMP", 0.01469);
            exchangeRates.put("IQD", 15.00172);
            exchangeRates.put("IRR", 482.75862);
            exchangeRates.put("ISK", 1.56462);
            exchangeRates.put("JEP", 0.01469);
            exchangeRates.put("JMD", 1.77397);
            exchangeRates.put("JOD", 0.00812);
            exchangeRates.put("KES", 1.48736);
            exchangeRates.put("KGS", 1.02604);
            exchangeRates.put("KHR", 46.61724);
            exchangeRates.put("KID", 0.01719);
            exchangeRates.put("KMF", 5.42463);
            exchangeRates.put("KWD", 0.00353);
            exchangeRates.put("KYD", 0.00956);
            exchangeRates.put("KZT", 5.14655);
            exchangeRates.put("LAK", 250.05747);
            exchangeRates.put("LBP", 1026.43678);
            exchangeRates.put("LKR", 3.49532);
            exchangeRates.put("LRD", 2.18739);
            exchangeRates.put("LSL", 0.21264);
            exchangeRates.put("LYD", 0.05505);
            exchangeRates.put("MAD", 0.11525);
            exchangeRates.put("MDL", 0.20216);
            exchangeRates.put("MGA", 51.43678);
            exchangeRates.put("MKD", 0.76499);
            exchangeRates.put("MMK", 24.06897);
            exchangeRates.put("MNT", 38.73563);
            exchangeRates.put("MOP", 0.09207);
            exchangeRates.put("MRU", 0.45517);
            exchangeRates.put("MUR", 0.52511);
            exchangeRates.put("MVR", 0.17637);
            exchangeRates.put("MWK", 19.88506);
            exchangeRates.put("MXN", 0.22936);
            exchangeRates.put("MYR", 0.05087);
            exchangeRates.put("MZN", 0.73082);
            exchangeRates.put("NAD", 0.21264);
            exchangeRates.put("NGN", 18.36207);
            exchangeRates.put("NIO", 0.42226);
            exchangeRates.put("NOK", 0.12164);
            exchangeRates.put("NPR", 1.6);
            exchangeRates.put("NZD", 0.01871);
            exchangeRates.put("OMR", 0.00441);
            exchangeRates.put("PAB", 0.01146);
            exchangeRates.put("PEN", 0.04254);
            exchangeRates.put("PGK", 0.04529);
            exchangeRates.put("PHP", 0.66438);
            exchangeRates.put("PLN", 0.04506);
            exchangeRates.put("PYG", 88.50574);
            exchangeRates.put("QAR", 0.04172);
            exchangeRates.put("RON", 0.05608);
            exchangeRates.put("RSD", 1.34589);
            exchangeRates.put("RUB", 1.11207);
            exchangeRates.put("RWF", 15.41187);
            exchangeRates.put("SAR", 0.04299);
            exchangeRates.put("SBD", 0.09589);
            exchangeRates.put("SCR", 0.15540);
            exchangeRates.put("SDG", 6.37816);
            exchangeRates.put("SEK", 0.11674);
            exchangeRates.put("SGD", 0.01529);
            exchangeRates.put("SHP", 0.01469);
            exchangeRates.put("SLL", 255.17241);
            exchangeRates.put("SOS", 6.55172);
            exchangeRates.put("SRD", 0.40920);
            exchangeRates.put("SSP", 12.64368);
            exchangeRates.put("STN", 0.30529);
            exchangeRates.put("SYP", 148.27586);
            exchangeRates.put("SZL", 0.21264);
            exchangeRates.put("TJS", 0.12533);
            exchangeRates.put("TMT", 0.04011);
            exchangeRates.put("TND", 0.03557);
            exchangeRates.put("TOP", 0.02728);
            exchangeRates.put("TRY", 0.39195);
            exchangeRates.put("TTD", 0.07768);
            exchangeRates.put("TVD", 0.01719);
            exchangeRates.put("TWD", 0.36207);
            exchangeRates.put("TZS", 31.03448);
            exchangeRates.put("UAH", 0.47126);
            exchangeRates.put("UGX", 43.65517);
            exchangeRates.put("UYU", 0.44655);
            exchangeRates.put("UZS", 144.82759);
            exchangeRates.put("VES", 0.42341);
            exchangeRates.put("VND", 281.03448);
            exchangeRates.put("VUV", 1.37471);
            exchangeRates.put("WST", 0.03125);
            exchangeRates.put("XAF", 6.87356);
            exchangeRates.put("XCD", 0.03098);
            exchangeRates.put("XDR", 0.00862);
            exchangeRates.put("XOF", 6.87356);
            exchangeRates.put("XPF", 1.48448);
            exchangeRates.put("YER", 2.87011);
            exchangeRates.put("ZAR", 0.21264);
            exchangeRates.put("ZMW", 0.30414);
            exchangeRates.put("ZWL", 0.15632);
        }

        private void initializeReserves() {
            foreignExchangeReserves.put("USD", 50000.0);
            foreignExchangeReserves.put("EUR", 20000.0);
        }

        String getCurrentDate() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
    }

    static class Customer {
        String customerId, name, country, email, phone, occupation, passwordHash, role;
        Map<String, String> documents = new HashMap<>();
        String status;
        boolean twoFactorEnabled;

        Customer(String customerId, String name, String country, String email, String phone, String occupation, String password, String role) {
            this.customerId = customerId;
            this.name = name;
            this.country = country;
            this.email = email;
            this.phone = phone;
            this.occupation = occupation;
            this.passwordHash = hashPassword(password);
            this.role = role;
            this.status = "Pending";
            this.twoFactorEnabled = false;
        }

        void addDocument(String docType, String docValue) {
            documents.put(docType, encryptData(docValue));
        }

        boolean authenticate(String password, String twoFactorCode) {
            boolean passMatch = hashPassword(password).equals(passwordHash);
            if (twoFactorEnabled) {
                return passMatch && verifyTwoFactor(twoFactorCode);
            }
            return passMatch;
        }
    }

    static class Account {
        String accountId, customerId, type;
        double balance, interestRate;
        String currency, status;
        Date createdDate;

        Account(String accountId, String customerId, String type, double balance, double interestRate, String currency) {
            this.accountId = accountId;
            this.customerId = customerId;
            this.type = type;
            this.balance = balance;
            this.interestRate = interestRate;
            this.currency = currency;
            this.status = "Active";
            this.createdDate = new Date();
        }
    }

    static class Loan {
        String loanId, customerId, type;
        double amount, interestRate, remainingBalance;
        int tenureYears;
        String status;

        Loan(String loanId, String customerId, String type, double amount, double interestRate, int tenureYears) {
            this.loanId = loanId;
            this.customerId = customerId;
            this.type = type;
            this.amount = amount;
            this.interestRate = interestRate;
            this.tenureYears = tenureYears;
            this.status = "Pending";
            this.remainingBalance = amount;
        }
    }

    static class SectorLoan {
        String loanId, customerId, sector;
        double amount, subsidizedRate, remainingBalance;
        int tenureYears;
        String status;

        SectorLoan(String loanId, String customerId, String sector, double amount, double subsidizedRate, int tenureYears) {
            this.loanId = loanId;
            this.customerId = customerId;
            this.sector = sector;
            this.amount = amount;
            this.subsidizedRate = subsidizedRate;
            this.tenureYears = tenureYears;
            this.status = "Pending";
            this.remainingBalance = amount;
        }
    }

    static class MicrofinanceLoan {
        String loanId, customerId;
        double amount, interestRate;
        int tenureMonths;
        String status;

        MicrofinanceLoan(String loanId, String customerId, double amount, double interestRate, int tenureMonths) {
            this.loanId = loanId;
            this.customerId = customerId;
            this.amount = amount;
            this.interestRate = interestRate;
            this.tenureMonths = tenureMonths;
            this.status = "Active";
        }
    }

    static class Card {
        String cardId, customerId, type;
        double limit;
        String status;

        Card(String cardId, String customerId, String type, double limit) {
            this.cardId = cardId;
            this.customerId = customerId;
            this.type = type;
            this.limit = limit;
            this.status = "Active";
        }
    }

    static class Insurance {
        String insuranceId, customerId, type;
        double premium, coverage;
        String status;

        Insurance(String insuranceId, String customerId, String type, double premium, double coverage) {
            this.insuranceId = insuranceId;
            this.customerId = customerId;
            this.type = type;
            this.premium = premium;
            this.coverage = coverage;
            this.status = "Active";
        }
    }

    static class Investment {
        String investmentId, customerId, type;
        double amount, returnRate;
        int tenureYears;

        Investment(String investmentId, String customerId, String type, double amount, double returnRate, int tenureYears) {
            this.investmentId = investmentId;
            this.customerId = customerId;
            this.type = type;
            this.amount = amount;
            this.returnRate = returnRate;
            this.tenureYears = tenureYears;
        }
    }

    static class Locker {
        String lockerId, customerId;
        boolean isAccessGranted;
        String size;

        Locker(String lockerId, String customerId, String size) {
            this.lockerId = lockerId;
            this.customerId = customerId;
            this.size = size;
            this.isAccessGranted = false;
        }
    }

    static class CommercialBank {
        String bankId, name;
        double capitalReserve;
        String status;

        CommercialBank(String bankId, String name, double capitalReserve) {
            this.bankId = bankId;
            this.name = name;
            this.capitalReserve = capitalReserve;
            this.status = "Active";
        }
    }

    static class Transaction {
        String transactionId, accountId, type;
        double amount;
        String currency, date, description;

        Transaction(String transactionId, String accountId, String type, double amount, String currency, String description) {
            this.transactionId = transactionId;
            this.accountId = accountId;
            this.type = type;
            this.amount = amount;
            this.currency = currency;
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            this.description = description;
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Hashing failed: " + e.getMessage());
            return password;
        }
    }

    private static String encryptData(String data) {
        String key = "secretkey";
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append((char) (data.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encrypted.toString();
    }

    private static boolean verifyTwoFactor(String code) {
        return code.equals("123456");
    }

    private static void logActivity(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " - " + message + "\n");
        } catch (IOException e) {
            System.err.println("Error logging activity: " + e.getMessage());
        }
    }

    private static void saveTransaction(Bank bank, Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_FILE, true))) {
            writer.write(String.format("%s,%s,%s,%s,%.2f,%s,%s\n",
                    transaction.transactionId, transaction.accountId, transaction.type,
                    transaction.currency, transaction.amount, transaction.date, transaction.description));
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
        bank.transactions.put(transaction.transactionId, transaction);
    }

    public static void main(String[] args) {
        MonolithicBankingSystem system = new MonolithicBankingSystem();
        System.out.println("=== Welcome to Monolithic Banking System ===");
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1: system.manageCustomer(); break;
                case 2: system.manageAccounts(); break;
                case 3: system.manageLoans(); break;
                case 4: system.manageSectorLoans(); break;
                case 5: system.manageMicrofinance(); break;
                case 6: system.manageCards(); break;
                case 7: system.manageInsurance(); break;
                case 8: system.manageInvestments(); break;
                case 9: system.manageLockers(); break;
                case 10: system.managePayments(); break;
                case 11: system.manageForex(); break;
                case 12: system.manageTaxes(); break;
                case 13: system.managePersonalBanking(); break;
                case 14: system.manageBusinessBanking(); break;
                case 15: system.manageInternationalServices(); break;
                case 16: system.manageOtherServices(); break;
                case 17: system.manageBankServices(); break;
                case 0: System.out.println("Exiting..."); scanner.close(); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== Monolithic Banking System Menu ===");
        System.out.println("1. Customer Management");
        System.out.println("2. Account Management");
        System.out.println("3. Loan Services");
        System.out.println("4. Sector-Specific Loans");
        System.out.println("5. Microfinance Services");
        System.out.println("6. Card Services");
        System.out.println("7. Insurance Services");
        System.out.println("8. Investment & Wealth Management");
        System.out.println("9. Safe Deposit Lockers");
        System.out.println("10. Payment & Fund Transfer");
        System.out.println("11. Forex & Remittance");
        System.out.println("12. Tax Calculators");
        System.out.println("13. Personal Banking");
        System.out.println("14. Business Banking");
        System.out.println("15. International Services (Including Cross-Bank Transfers)");
        System.out.println("16. Other Services");
        System.out.println("17. Monolithic Bank Services()");
        System.out.println("0. Exit");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int input = scanner.nextInt();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a number.");
            return -1;
        }
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        try {
            double input = scanner.nextDouble();
            scanner.nextLine();
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Invalid input. Please enter a valid number.");
            return -1;
        }
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private Bank selectBank() {
        System.out.println("\nSelect a Country:");
        String[] countries = {"Argentina", "Canada", "Europe", "Germany", "Africa", "China", "France", "Mexico", "India", "North America", "Japan", "South America"};
        for (int i = 0; i < countries.length; i++) {
            System.out.println((i + 1) + ". " + countries[i]);
        }
        int countryChoice = getIntInput("Choice: ");
        if (countryChoice < 1 || countryChoice > countries.length) {
            System.out.println("Invalid country selection!");
            return null;
        }
        String selectedCountry = countries[countryChoice - 1];

        System.out.println("\nSelect a Bank in " + selectedCountry + ":");
        List<Bank> countryBanks = new ArrayList<>();
        for (Bank bank : banks.values()) {
            if (bank.country.equals(selectedCountry)) {
                countryBanks.add(bank);
            }
        }
        for (int i = 0; i < countryBanks.size(); i++) {
            Bank bank = countryBanks.get(i);
            System.out.println((i + 1) + ". " + bank.bankName + " (" + bank.currency + ")");
        }
        int bankChoice = getIntInput("Choice: ");
        if (bankChoice < 1 || bankChoice > countryBanks.size()) {
            System.out.println("Invalid bank selection!");
            return null;
        }
        return countryBanks.get(bankChoice - 1);
    }

    private void manageCustomer() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Register Customer\n2. Login\n3. View Customer (Admin)");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: registerCustomer(bank); break;
            case 2: loginCustomer(bank); break;
            case 3: viewCustomer(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void registerCustomer(Bank bank) {
        String name = getStringInput("Name: ");
        if (name.isEmpty()) { System.out.println("Name cannot be empty!"); return; }
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");
        String occupation = getStringInput("Occupation: ");
        String password = getStringInput("Password: ");
        if (password.isEmpty()) { System.out.println("Password cannot be empty!"); return; }
        String role = getStringInput("Role (customer/admin/employee): ");
        String customerId = generateId(bank, "C", bank.customers.size());
        Customer customer = new Customer(customerId, name, bank.country, email, phone, occupation, password, role);
        collectCustomerDocuments(customer);
        bank.customers.put(customerId, customer);
        logActivity("Customer registered: " + customerId);
        System.out.println("Customer " + customerId + " registered! Awaiting KYC approval.");
    }

    private void loginCustomer(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        String password = getStringInput("Password: ");
        Customer customer = bank.customers.get(customerId);
        if (customer != null) {
            String twoFactorCode = customer.twoFactorEnabled ? getStringInput("2FA Code: ") : "";
            if (customer.authenticate(password, twoFactorCode)) {
                System.out.println("Login successful! Welcome, " + customer.name);
                logActivity("Login successful: " + customerId);
                if (customer.status.equals("Active")) {
                    customerMenu(bank, customer);
                } else {
                    System.out.println("Account pending KYC approval.");
                }
            } else {
                System.out.println("Authentication failed!");
                logActivity("Login failed: " + customerId);
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    private void viewCustomer(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer != null) {
            System.out.println("ID: " + customer.customerId + " | Name: " + customer.name + " | Status: " + customer.status);
            if (getStringInput("Approve KYC? (yes/no): ").equalsIgnoreCase("yes")) {
                customer.status = "Active";
                System.out.println("KYC approved!");
                logActivity("KYC approved for: " + customerId);
            }
        } else {
            System.out.println("Customer not found!");
        }
    }

    private void collectCustomerDocuments(Customer customer) {
        customer.addDocument("Identity", getStringInput("Identity Proof: "));
        customer.addDocument("Address", getStringInput("Address Proof: "));
    }

    private void customerMenu(Bank bank, Customer customer) {
        while (true) {
            System.out.println("\n1. View Accounts\n2. Deposit\n3. Withdraw\n4. Apply Loan\n5. View Transactions\n6. Use Bank Services\n0. Logout");
            int choice = getIntInput("Choice: ");
            if (choice == 0) break;
            switch (choice) {
                case 1: viewAccounts(bank, customer); break;
                case 2: deposit(bank, customer); break;
                case 3: withdraw(bank, customer); break;
                case 4: applyForLoan(bank, customer); break;
                case 5: viewTransactionHistory(bank, customer); break;
                case 6: useBankServices(bank, customer); break;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    private void manageAccounts() {
        Bank bank = selectBank();
        if (bank == null) return;
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        System.out.println("\n1. Open Account\n2. View Accounts\n3. Deposit\n4. Withdraw");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: openAccount(bank, customer); break;
            case 2: viewAccounts(bank, customer); break;
            case 3: deposit(bank, customer); break;
            case 4: withdraw(bank, customer); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void openAccount(Bank bank, Customer customer) {
        System.out.println("1. Savings\n2. Current\n3. Fixed Deposit\n4. Recurring Deposit");
        int typeChoice = getIntInput("Type: ");
        String type = switch (typeChoice) {
            case 1 -> "Savings";
            case 2 -> "Current";
            case 3 -> "Fixed Deposit";
            case 4 -> "Recurring Deposit";
            default -> "Savings";
        };
        double balance = getDoubleInput("Initial Balance: ");
        if (balance < 0) { System.out.println("Balance cannot be negative!"); return; }
        String accountId = generateId(bank, "A", bank.accounts.size());
        double interestRate = type.equals("Savings") ? 3.5 : type.equals("Fixed Deposit") ? 6.5 : 0.0;
        Account account = new Account(accountId, customer.customerId, type, balance, interestRate, bank.currency);
        bank.accounts.put(accountId, account);
        saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), accountId, "Deposit", balance, bank.currency, "Account Opening"));
        System.out.println("Account " + accountId + " opened!");
    }

    private void viewAccounts(Bank bank, Customer customer) {
        bank.accounts.values().stream()
                .filter(a -> a.customerId.equals(customer.customerId))
                .forEach(a -> System.out.println("ID: " + a.accountId + " | Type: " + a.type + " | Balance: " + df.format(a.balance) + " " + a.currency));
    }

    private void deposit(Bank bank, Customer customer) {
        String accountId = getStringInput("Account ID: ");
        Account account = bank.accounts.get(accountId);
        if (account != null && account.customerId.equals(customer.customerId)) {
            double amount = getDoubleInput("Amount: ");
            if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
            account.balance += amount;
            saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), accountId, "Deposit", amount, bank.currency, "Cash Deposit"));
            System.out.println("Deposited " + amount + " " + bank.currency);
        } else {
            System.out.println("Account not found or unauthorized!");
        }
    }

    private void withdraw(Bank bank, Customer customer) {
        String accountId = getStringInput("Account ID: ");
        Account account = bank.accounts.get(accountId);
        if (account != null && account.customerId.equals(customer.customerId)) {
            double amount = getDoubleInput("Amount: ");
            if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
            if (account.balance >= amount) {
                account.balance -= amount;
                saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), accountId, "Withdrawal", amount, bank.currency, "Cash Withdrawal"));
                System.out.println("Withdrawn " + amount + " " + bank.currency);
            } else {
                System.out.println("Insufficient balance!");
                logActivity("Failed withdrawal attempt: " + accountId);
            }
        } else {
            System.out.println("Account not found or unauthorized!");
        }
    }

    private void manageLoans() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Apply Loan\n2. View Loan\n3. Repay Loan");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: applyForLoan(bank); break;
            case 2: viewLoanStatus(bank); break;
            case 3: repayLoan(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void applyForLoan(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        applyForLoan(bank, customer);
    }

    private void applyForLoan(Bank bank, Customer customer) {
        System.out.println("1. Personal\n2. Home\n3. Car\n4. Education\n5. Business");
        int typeChoice = getIntInput("Type: ");
        String type = switch (typeChoice) {
            case 1 -> "Personal";
            case 2 -> "Home";
            case 3 -> "Car";
            case 4 -> "Education";
            case 5 -> "Business";
            default -> "Personal";
        };
        double amount = getDoubleInput("Amount: ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        int tenure = getIntInput("Tenure (years): ");
        if (tenure <= 0) { System.out.println("Tenure must be positive!"); return; }
        String loanId = generateId(bank, "L", bank.loans.size());
        Loan loan = new Loan(loanId, customer.customerId, type, amount, 7.0, tenure);
        bank.loans.put(loanId, loan);
        collectLoanDocuments(customer, type);
        System.out.println("Loan " + loanId + " applied!");
        logActivity("Loan applied: " + loanId);
    }

    private void viewLoanStatus(Bank bank) {
        String loanId = getStringInput("Loan ID: ");
        Loan loan = bank.loans.get(loanId);
        if (loan != null) {
            System.out.println("ID: " + loan.loanId + " | Type: " + loan.type + " | Amount: " + df.format(loan.amount) + " " + bank.currency + " | Remaining: " + df.format(loan.remainingBalance) + " | Status: " + loan.status);
        } else {
            System.out.println("Loan not found!");
        }
    }

    private void repayLoan(Bank bank) {
        String loanId = getStringInput("Loan ID: ");
        Loan loan = bank.loans.get(loanId);
        if (loan != null) {
            double amount = getDoubleInput("Repayment Amount: ");
            if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
            if (loan.remainingBalance >= amount) {
                loan.remainingBalance -= amount;
                if (loan.remainingBalance == 0) loan.status = "Closed";
                System.out.println("Repaid " + amount + " " + bank.currency);
                logActivity("Loan repayment: " + loanId + " - " + amount);
            } else {
                System.out.println("Amount exceeds remaining balance!");
            }
        } else {
            System.out.println("Loan not found!");
        }
    }

    private void collectLoanDocuments(Customer customer, String loanType) {
        System.out.println("Documents for " + loanType + ":");
        customer.addDocument("Identity", getStringInput("Identity Proof: "));
        if (loanType.equals("Home")) {
            customer.addDocument("Property", getStringInput("Property Docs: "));
        } else if (loanType.equals("Car")) {
            customer.addDocument("Vehicle", getStringInput("Vehicle Docs: "));
        }
    }

    private void manageSectorLoans() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Apply Sector-Specific Loan\n2. View Loan\n3. Repay Loan");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: applyForSectorLoan(bank); break;
            case 2: viewSectorLoanStatus(bank); break;
            case 3: repaySectorLoan(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void applyForSectorLoan(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        String sector = getStringInput("Sector (e.g., Agriculture, Business): ");
        double amount = getDoubleInput("Amount: ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        int tenure = getIntInput("Tenure (years): ");
        if (tenure <= 0) { System.out.println("Tenure must be positive!"); return; }
        double subsidizedRate = 5.0;
        String loanId = generateId(bank, "SL", bank.sectorLoans.size());
        SectorLoan loan = new SectorLoan(loanId, customer.customerId, sector, amount, subsidizedRate, tenure);
        bank.sectorLoans.put(loanId, loan);
        collectLoanDocuments(customer, sector);
        System.out.println(sector + " Loan " + loanId + " applied!");
        logActivity("Sector loan applied: " + loanId);
    }

    private void viewSectorLoanStatus(Bank bank) {
        String loanId = getStringInput("Loan ID: ");
        SectorLoan loan = bank.sectorLoans.get(loanId);
        if (loan != null) {
            System.out.println("ID: " + loan.loanId + " | Sector: " + loan.sector + " | Amount: " + df.format(loan.amount) + " " + bank.currency + " | Remaining: " + df.format(loan.remainingBalance) + " | Status: " + loan.status);
        } else {
            System.out.println("Loan not found!");
        }
    }

    private void repaySectorLoan(Bank bank) {
        String loanId = getStringInput("Loan ID: ");
        SectorLoan loan = bank.sectorLoans.get(loanId);
        if (loan != null) {
            double amount = getDoubleInput("Repayment Amount: ");
            if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
            if (loan.remainingBalance >= amount) {
                loan.remainingBalance -= amount;
                if (loan.remainingBalance == 0) loan.status = "Closed";
                System.out.println("Repaid " + amount + " " + bank.currency);
                logActivity("Sector loan repayment: " + loanId + " - " + amount);
            } else {
                System.out.println("Amount exceeds remaining balance!");
            }
        } else {
            System.out.println("Loan not found!");
        }
    }

    private void manageMicrofinance() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Apply Microfinance Loan\n2. View Microfinance Loan");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: applyForMicrofinance(bank); break;
            case 2: viewMicrofinanceLoan(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void applyForMicrofinance(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        double amount = getDoubleInput("Amount (Small Loan): ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        int tenureMonths = getIntInput("Tenure (months): ");
        if (tenureMonths <= 0) { System.out.println("Tenure must be positive!"); return; }
        String loanId = generateId(bank, "ML", bank.microLoans.size());
        MicrofinanceLoan loan = new MicrofinanceLoan(loanId, customer.customerId, amount, 6.0, tenureMonths);
        bank.microLoans.put(loanId, loan);
        System.out.println("Microfinance Loan " + loanId + " applied!");
        logActivity("Microfinance loan applied: " + loanId);
    }

    private void viewMicrofinanceLoan(Bank bank) {
        String loanId = getStringInput("Loan ID: ");
        MicrofinanceLoan loan = bank.microLoans.get(loanId);
        if (loan != null) {
            System.out.println("ID: " + loan.loanId + " | Amount: " + df.format(loan.amount) + " " + bank.currency + " | Status: " + loan.status);
        } else {
            System.out.println("Loan not found!");
        }
    }

    private void manageCards() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Apply Card\n2. View Card");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: applyForCard(bank); break;
            case 2: viewCard(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void applyForCard(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        System.out.println("1. Credit\n2. Debit\n3. Prepaid");
        int type = getIntInput("Type: ");
        String cardType = type == 1 ? "Credit" : type == 2 ? "Debit" : "Prepaid";
        double limit = type == 1 ? getDoubleInput("Credit Limit: ") : 0;
        if (limit < 0) { System.out.println("Limit cannot be negative!"); return; }
        String cardId = generateId(bank, "C", bank.cards.size());
        bank.cards.put(cardId, new Card(cardId, customer.customerId, cardType, limit));
        System.out.println(cardType + " Card " + cardId + " applied!");
        logActivity("Card applied: " + cardId);
    }

    private void viewCard(Bank bank) {
        String cardId = getStringInput("Card ID: ");
        Card card = bank.cards.get(cardId);
        if (card != null) {
            System.out.println("ID: " + card.cardId + " | Type: " + card.type + " | Limit: " + df.format(card.limit) + " " + bank.currency + " | Status: " + card.status);
        } else {
            System.out.println("Card not found!");
        }
    }

    private void manageInsurance() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Life Insurance\n2. Health Insurance\n3. Car Insurance\n4. Home Insurance");
        int choice = getIntInput("Choice: ");
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        switch (choice) {
            case 1: processLifeInsurance(bank, customer); break;
            case 2: processHealthInsurance(bank, customer); break;
            case 3: processCarInsurance(bank, customer); break;
            case 4: processHomeInsurance(bank, customer); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void processLifeInsurance(Bank bank, Customer customer) {
        int age = getIntInput("Age: ");
        if (age <= 0) { System.out.println("Age must be positive!"); return; }
        double premium = age < 25 ? 1500 : age <= 40 ? 3000 : 5000;
        String insuranceId = generateId(bank, "I", bank.insurances.size());
        bank.insurances.put(insuranceId, new Insurance(insuranceId, customer.customerId, "Life", premium, 1000000));
        collectInsuranceDocuments(customer, "Life");
        System.out.println("Life Insurance Premium: " + premium + " " + bank.currency);
        logActivity("Life insurance applied: " + insuranceId);
    }

    private void processHealthInsurance(Bank bank, Customer customer) {
        double premium = 2000;
        String insuranceId = generateId(bank, "I", bank.insurances.size());
        bank.insurances.put(insuranceId, new Insurance(insuranceId, customer.customerId, "Health", premium, 500000));
        collectInsuranceDocuments(customer, "Health");
        System.out.println("Health Insurance Premium: " + premium + " " + bank.currency);
        logActivity("Health insurance applied: " + insuranceId);
    }

    private void processCarInsurance(Bank bank, Customer customer) {
        double premium = 1000;
        String insuranceId = generateId(bank, "I", bank.insurances.size());
        bank.insurances.put(insuranceId, new Insurance(insuranceId, customer.customerId, "Car", premium, 200000));
        collectInsuranceDocuments(customer, "Car");
        System.out.println("Car Insurance Premium: " + premium + " " + bank.currency);
        logActivity("Car insurance applied: " + insuranceId);
    }

    private void processHomeInsurance(Bank bank, Customer customer) {
        double premium = 1500;
        String insuranceId = generateId(bank, "I", bank.insurances.size());
        bank.insurances.put(insuranceId, new Insurance(insuranceId, customer.customerId, "Home", premium, 300000));
        collectInsuranceDocuments(customer, "Home");
        System.out.println("Home Insurance Premium: " + premium + " " + bank.currency);
        logActivity("Home insurance applied: " + insuranceId);
    }

    private void collectInsuranceDocuments(Customer customer, String insuranceType) {
        customer.addDocument("Identity", getStringInput("Identity Proof: "));
        if (insuranceType.equals("Car")) {
            customer.addDocument("Vehicle", getStringInput("Vehicle Registration: "));
        } else if (insuranceType.equals("Home")) {
            customer.addDocument("Property", getStringInput("Property Docs: "));
        }
    }

    private void manageInvestments() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Open Investment\n2. View Investments");
        int choice = getIntInput("Choice: ");
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        switch (choice) {
            case 1: openInvestment(bank, customer); break;
            case 2: viewInvestments(bank, customer); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void openInvestment(Bank bank, Customer customer) {
        System.out.println("1. Mutual Funds\n2. Fixed Deposits\n3. Stocks");
        int typeChoice = getIntInput("Type: ");
        String type = switch (typeChoice) {
            case 1 -> "Mutual Funds";
            case 2 -> "Fixed Deposits";
            case 3 -> "Stocks";
            default -> "Mutual Funds";
        };
        double amount = getDoubleInput("Amount: ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        int tenure = getIntInput("Tenure (years): ");
        if (tenure <= 0) { System.out.println("Tenure must be positive!"); return; }
        double returnRate = type.equals("Mutual Funds") ? 8.0 : type.equals("Fixed Deposits") ? 6.0 : 12.0;
        String investmentId = generateId(bank, "INV", bank.investments.size());
        bank.investments.put(investmentId, new Investment(investmentId, customer.customerId, type, amount, returnRate, tenure));
        System.out.println(type + " Investment " + investmentId + " opened!");
        logActivity("Investment opened: " + investmentId);
    }

    private void viewInvestments(Bank bank, Customer customer) {
        bank.investments.values().stream()
                .filter(i -> i.customerId.equals(customer.customerId))
                .forEach(i -> System.out.println("ID: " + i.investmentId + " | Type: " + i.type + " | Amount: " + df.format(i.amount) + " " + bank.currency + " | Return Rate: " + i.returnRate + "%"));
    }

    private void manageLockers() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Book Locker\n2. Access Locker");
        int choice = getIntInput("Choice: ");
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        switch (choice) {
            case 1: bookLocker(bank, customer); break;
            case 2: accessLocker(bank, customer); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void bookLocker(Bank bank, Customer customer) {
        String size = getStringInput("Locker Size (Small/Medium/Large): ");
        String lockerId = generateId(bank, "LCK", bank.lockers.size());
        bank.lockers.put(lockerId, new Locker(lockerId, customer.customerId, size));
        System.out.println("Locker " + lockerId + " booked!");
        logActivity("Locker booked: " + lockerId);
    }

    private void accessLocker(Bank bank, Customer customer) {
        String lockerId = getStringInput("Locker ID: ");
        Locker locker = bank.lockers.get(lockerId);
        if (locker != null && locker.customerId.equals(customer.customerId)) {
            locker.isAccessGranted = true;
            System.out.println("Access granted to locker " + lockerId);
            logActivity("Locker accessed: " + lockerId);
        } else {
            System.out.println("Locker not found or unauthorized!");
        }
    }

    private void managePayments() {
        Bank bank = selectBank();
        if (bank == null) return;
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        String accountId = getStringInput("Account ID: ");
        Account account = bank.accounts.get(accountId);
        if (account == null || !account.customerId.equals(customer.customerId)) {
            System.out.println("Account not found or unauthorized!");
            return;
        }
        double amount = getDoubleInput("Amount: ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        if (account.balance >= amount) {
            String recipientAccountId = getStringInput("Recipient Account ID: ");
            Account recipient = bank.accounts.get(recipientAccountId);
            if (recipient != null) {
                account.balance -= amount;
                recipient.balance += amount;
                saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), accountId, "Transfer", amount, bank.currency, "Payment to " + recipientAccountId));
                saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), recipientAccountId, "Deposit", amount, bank.currency, "Received from " + accountId));
                System.out.println("Transferred " + amount + " " + bank.currency + " to " + recipientAccountId);
                logActivity("Payment: " + accountId + " -> " + recipientAccountId + " - " + amount);
            } else {
                System.out.println("Recipient account not found!");
            }
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private void manageForex() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. View Exchange Rates\n2. Convert Currency");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: viewExchangeRates(bank); break;
            case 2: convertCurrency(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void viewExchangeRates(Bank bank) {
        System.out.println("Exchange Rates (Base: INR):");
        bank.exchangeRates.forEach((currency, rate) -> System.out.println(currency + ": " + df.format(rate)));
    }

    private void convertCurrency(Bank bank) {
        String fromCurrency = getStringInput("From Currency: ");
        String toCurrency = getStringInput("To Currency: ");
        double amount = getDoubleInput("Amount: ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        Double fromRate = bank.exchangeRates.get(fromCurrency);
        Double toRate = bank.exchangeRates.get(toCurrency);
        if (fromRate != null && toRate != null) {
            double inrValue = amount / fromRate;
            double convertedAmount = inrValue * toRate;
            System.out.println(amount + " " + fromCurrency + " = " + df.format(convertedAmount) + " " + toCurrency);
            logActivity("Forex conversion: " + amount + " " + fromCurrency + " to " + convertedAmount + " " + toCurrency);
        } else {
            System.out.println("Invalid currency!");
        }
    }

    private void manageTaxes() {
        Bank bank = selectBank();
        if (bank == null) return;
        double income = getDoubleInput("Annual Income (" + bank.currency + "): ");
        if (income <= 0) { System.out.println("Income must be positive!"); return; }
        double taxRate = income < 10000 ? 10 : income < 50000 ? 20 : 30;
        double tax = income * (taxRate / 100);
        System.out.println("Tax Rate: " + taxRate + "% | Tax Payable: " + df.format(tax) + " " + bank.currency);
        logActivity("Tax calculated: Income " + income + " " + bank.currency + " -> Tax " + tax);
    }

    private void managePersonalBanking() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. View Accounts\n2. Apply for Personal Loan\n3. Manage Cards");
        int choice = getIntInput("Choice: ");
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        switch (choice) {
            case 1: viewAccounts(bank, customer); break;
            case 2: applyForLoan(bank, customer); break;
            case 3: applyForCard(bank); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void manageBusinessBanking() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Open Business Account\n2. Apply Business Loan\n3. View Business Accounts");
        int choice = getIntInput("Choice: ");
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        switch (choice) {
            case 1: openBusinessAccount(bank, customer); break;
            case 2: applyForBusinessLoan(bank, customer); break;
            case 3: viewAccounts(bank, customer); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void openBusinessAccount(Bank bank, Customer customer) {
        double balance = getDoubleInput("Initial Balance: ");
        if (balance < 0) { System.out.println("Balance cannot be negative!"); return; }
        String accountId = generateId(bank, "BA", bank.accounts.size());
        Account account = new Account(accountId, customer.customerId, "Business", balance, 2.0, bank.currency);
        bank.accounts.put(accountId, account);
        saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), accountId, "Deposit", balance, bank.currency, "Business Account Opening"));
        System.out.println("Business Account " + accountId + " opened!");
        logActivity("Business account opened: " + accountId);
    }

    private void applyForBusinessLoan(Bank bank, Customer customer) {
        double amount = getDoubleInput("Amount: ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        int tenure = getIntInput("Tenure (years): ");
        if (tenure <= 0) { System.out.println("Tenure must be positive!"); return; }
        String loanId = generateId(bank, "BL", bank.loans.size());
        Loan loan = new Loan(loanId, customer.customerId, "Business", amount, 8.0, tenure);
        bank.loans.put(loanId, loan);
        collectLoanDocuments(customer, "Business");
        System.out.println("Business Loan " + loanId + " applied!");
        logActivity("Business loan applied: " + loanId);
    }

    private void manageInternationalServices() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. International Transfer (Same Bank)\n2. Forex Conversion\n3. International Bank Transfer (Different Banks)");
        int choice = getIntInput("Choice: ");
        switch (choice) {
            case 1: internationalTransfer(bank); break;
            case 2: convertCurrency(bank); break;
            case 3: internationalBankTransfer(); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void internationalTransfer(Bank bank) {
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        String accountId = getStringInput("Account ID: ");
        Account account = bank.accounts.get(accountId);
        if (account == null || !account.customerId.equals(customer.customerId)) {
            System.out.println("Account not found or unauthorized!");
            return;
        }
        double amount = getDoubleInput("Amount (" + bank.currency + "): ");
        if (amount <= 0) { System.out.println("Amount must be positive!"); return; }
        String toCurrency = getStringInput("To Currency: ");
        Double toRate = bank.exchangeRates.get(toCurrency);
        if (toRate == null) {
            System.out.println("Invalid currency!");
            return;
        }
        if (account.balance >= amount) {
            double convertedAmount = (amount / bank.exchangeRates.get(bank.currency)) * toRate;
            account.balance -= amount;
            saveTransaction(bank, new Transaction(generateId(bank, "T", bank.transactions.size()), accountId, "International Transfer", amount, bank.currency, "To " + toCurrency + ": " + df.format(convertedAmount)));
            System.out.println("Transferred " + amount + " " + bank.currency + " (" + df.format(convertedAmount) + " " + toCurrency + ")");
            logActivity("International transfer: " + amount + " " + bank.currency + " to " + convertedAmount + " " + toCurrency);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    private void internationalBankTransfer() {
        System.out.println("\n=== International Bank Transfer ===");
        
        // Select source bank
        System.out.println("Select Source Bank:");
        Bank sourceBank = selectBank();
        if (sourceBank == null) return;

        // Get source customer and account
        String sourceCustomerId = getStringInput("Source Customer ID: ");
        Customer sourceCustomer = sourceBank.customers.get(sourceCustomerId);
        if (sourceCustomer == null || !sourceCustomer.status.equals("Active")) {
            System.out.println("Source customer not found or not active!");
            return;
        }

        String sourceAccountId = getStringInput("Source Account ID: ");
        Account sourceAccount = sourceBank.accounts.get(sourceAccountId);
        if (sourceAccount == null || !sourceAccount.customerId.equals(sourceCustomer.customerId)) {
            System.out.println("Source account not found or unauthorized!");
            return;
        }

        // Select destination bank
        System.out.println("Select Destination Bank:");
        Bank destBank = selectBank();
        if (destBank == null) return;

        // Get destination account
        String destAccountId = getStringInput("Destination Account ID: ");
        Account destAccount = destBank.accounts.get(destAccountId);
        if (destAccount == null) {
            System.out.println("Destination account not found!");
            return;
        }

        // Get transfer details
        double amount = getDoubleInput("Amount to Transfer (" + sourceBank.currency + "): ");
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }

        if (sourceAccount.balance < amount) {
            System.out.println("Insufficient balance!");
            return;
        }

        // Perform currency conversion if needed
        double convertedAmount = amount;
        if (!sourceBank.currency.equals(destBank.currency)) {
            Double sourceRate = sourceBank.exchangeRates.get(sourceBank.currency);
            Double destRate = destBank.exchangeRates.get(destBank.currency);
            
            if (sourceRate == null || destRate == null) {
                System.out.println("Exchange rate not available!");
                return;
            }

            // Convert to INR first, then to destination currency
            double inrValue = amount / sourceRate;
            convertedAmount = inrValue * destRate;
        }

        // Execute transfer
        sourceAccount.balance -= amount;
        destAccount.balance += convertedAmount;

        // Record transactions
        String sourceTransId = generateId(sourceBank, "T", sourceBank.transactions.size());
        String destTransId = generateId(destBank, "T", destBank.transactions.size());

        saveTransaction(sourceBank, new Transaction(
            sourceTransId,
            sourceAccountId,
            "International Transfer Out",
            amount,
            sourceBank.currency,
            "To " + destBank.bankName + " Account: " + destAccountId + " (" + df.format(convertedAmount) + " " + destBank.currency + ")"
        ));

        saveTransaction(destBank, new Transaction(
            destTransId,
            destAccountId,
            "International Transfer In",
            convertedAmount,
            destBank.currency,
            "From " + sourceBank.bankName + " Account: " + sourceAccountId + " (" + df.format(amount) + " " + sourceBank.currency + ")"
        ));

        // Log the activity
        logActivity("International transfer: " + amount + " " + sourceBank.currency + 
                    " from " + sourceBank.bankName + " (" + sourceAccountId + ")" +
                    " to " + convertedAmount + " " + destBank.currency + 
                    " at " + destBank.bankName + " (" + destAccountId + ")");

        System.out.println("Transfer successful!");
        System.out.println("Sent: " + df.format(amount) + " " + sourceBank.currency);
        System.out.println("Received: " + df.format(convertedAmount) + " " + destBank.currency);
    }

        private void manageOtherServices() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\n1. Enable 2FA\n2. View Transaction History");
        int choice = getIntInput("Choice: ");
        String customerId = getStringInput("Customer ID: ");
        Customer customer = bank.customers.get(customerId);
        if (customer == null || !customer.status.equals("Active")) {
            System.out.println("Customer not found or not active!");
            return;
        }
        switch (choice) {
            case 1: enableTwoFactor(bank, customer); break;
            case 2: viewTransactionHistory(bank, customer); break;
            default: System.out.println("Invalid choice!");
        }
    }

    private void enableTwoFactor(Bank bank, Customer customer) {
        customer.twoFactorEnabled = true;
        System.out.println("Two-Factor Authentication enabled!");
        logActivity("2FA enabled for customer: " + customer.customerId);
    }

    private void viewTransactionHistory(Bank bank, Customer customer) {
        System.out.println("\nTransaction History for " + customer.name + ":");
        bank.transactions.values().stream()
            .filter(t -> bank.accounts.containsKey(t.accountId) && 
                        bank.accounts.get(t.accountId).customerId.equals(customer.customerId))
            .forEach(t -> System.out.println(
                "ID: " + t.transactionId + 
                " | Date: " + t.date + 
                " | Type: " + t.type + 
                " | Amount: " + df.format(t.amount) + " " + t.currency + 
                " | Description: " + t.description
            ));
    }

    private void manageBankServices() {
        Bank bank = selectBank();
        if (bank == null) return;
        System.out.println("\nAvailable Services at " + bank.bankName + ":");
        for (int i = 0; i < bank.services.size(); i++) {
            System.out.println((i + 1) + ". " + bank.services.get(i));
        }
        int choice = getIntInput("Select Service to Use (0 to exit): ");
        if (choice > 0 && choice <= bank.services.size()) {
            BankService service = bank.services.get(choice - 1);
            String customerId = getStringInput("Customer ID: ");
            Customer customer = bank.customers.get(customerId);
            if (customer == null || !customer.status.equals("Active")) {
                System.out.println("Customer not found or not active!");
                return;
            }
            useBankService(bank, customer, service);
        }
    }

    private void useBankService(Bank bank, Customer customer, BankService service) {
        System.out.println("Using " + service.name + " for " + customer.name);
        if (service.name.contains("Account")) {
            openAccount(bank, customer);
        } else if (service.name.equals("Loans")) {
            applyForLoan(bank, customer);
        } else if (service.name.equals("Credit Cards")) {
            applyForCard(bank);
        } else {
            System.out.println("Service activated: " + service.functionality);
            logActivity("Service used: " + service.name + " by " + customer.customerId);
        }
    }

    private void useBankServices(Bank bank, Customer customer) {
        System.out.println("\nAvailable Services:");
        for (int i = 0; i < bank.services.size(); i++) {
            System.out.println((i + 1) + ". " + bank.services.get(i));
        }
        int choice = getIntInput("Select Service (0 to exit): ");
        if (choice > 0 && choice <= bank.services.size()) {
            useBankService(bank, customer, bank.services.get(choice - 1));
        }
    }

    private String generateId(Bank bank, String prefix, int count) {
        String bankCode = bank.bankName.substring(0, 3).toUpperCase();
        return String.format("%s-%s-%06d", prefix, bankCode, count + 1);
    }
}
