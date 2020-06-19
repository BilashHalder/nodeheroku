  try {
            ethGetBalance =  web3.ethGetBalance(strAddress,DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger wei = ethGetBalance.getBalance();
            Toast toast=Toast.makeText(getApplicationContext(),wei.toString(),Toast.LENGTH_SHORT);
            toast.show();

        } catch (ExecutionException e) {
            Toast toast=Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT);
            toast.show();
        } catch (InterruptedException e) {
            Toast toast=Toast.makeText(getApplicationContext(),"2nd",Toast.LENGTH_SHORT);
            toast.show();
        }




   try {
            EthBlockNumber result = web3.ethBlockNumber().sendAsync().get();
            Toast toast=Toast.makeText(getApplicationContext(),result.getBlockNumber().toString(),Toast.LENGTH_SHORT);
            toast.show();
        } catch (ExecutionException e) {
            Toast toast=Toast.makeText(getApplicationContext(),"2nd",Toast.LENGTH_SHORT);
            toast.show();
        } catch (InterruptedException e) {
            Toast toast=Toast.makeText(getApplicationContext(),"3rd",Toast.LENGTH_SHORT);
            toast.show();
        }




        Toast toast=Toast.makeText(getApplicationContext(),credentials.getEcKeyPair().toString(),Toast.LENGTH_LONG);
        toast.show();


web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();















// Transaction.java
package io.kauri.tutorials.java_ethereum;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

public class Transaction {

  public static void main(String[] args)  {

    System.out.println("Connecting to Ethereum ...");
    Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/083836b2784f48e19e03487eb3209923"));
    System.out.println("Successfuly connected to Ethereum");

    try {
      String pk = "CHANGE_ME"; // Add a private key here

      // Decrypt and open the wallet into a Credential object
      Credentials credentials = Credentials.create(pk);
      System.out.println("Account address: " + credentials.getAddress());
      System.out.println("Balance: " + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Unit.ETHER));

      // Get the latest nonce
      EthGetTransactionCount ethGetTransactionCount = web3.ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
      BigInteger nonce =  ethGetTransactionCount.getTransactionCount();

      // Recipient address
      String recipientAddress = "0xAA6325C45aE6fAbD028D19fa1539663Df14813a8";

      // Value to transfer (in wei)
      BigInteger value = Convert.toWei("1", Unit.ETHER).toBigInteger();

      // Gas Parameters
      BigInteger gasLimit = BigInteger.valueOf(21000);
      BigInteger gasPrice = Convert.toWei("1", Unit.GWEI).toBigInteger();

      // Prepare the rawTransaction
      RawTransaction rawTransaction  = RawTransaction.createEtherTransaction(
                 nonce,
                 gasPrice,
                 gasLimit,
                 recipientAddress,
                 value);

      // Sign the transaction
      byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
      String hexValue = Numeric.toHexString(signedMessage);

      // Send transaction
      EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).send();
      String transactionHash = ethSendTransaction.getTransactionHash();
      System.out.println("transactionHash: " + transactionHash);

      // Wait for transaction to be mined
      Optional<TransactionReceipt> transactionReceipt = null;
      do {
        System.out.println("checking if transaction " + transactionHash + " is mined....");
            EthGetTransactionReceipt ethGetTransactionReceiptResp = web3.ethGetTransactionReceipt(transactionHash).send();
            transactionReceipt = ethGetTransactionReceiptResp.getTransactionReceipt();
            Thread.sleep(3000); // Wait 3 sec
      } while(!transactionReceipt.isPresent());

      System.out.println("Transaction " + transactionHash + " was mined in block # " + transactionReceipt.get().getBlockNumber());
      System.out.println("Balance: " + Convert.fromWei(web3.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST).send().getBalance().toString(), Unit.ETHER));


    } catch (IOException | InterruptedException ex) {
      throw new RuntimeException(ex);
    }
  }
}











     
     
     
     
     
   String password = null; // no encryption
        String mnemonic = "candy maple cake sugar pudding cream honey rich smooth crumble sweet treat";

        //Credentials credentials = WalletUtils.loadBip39Credentials(password, mnemonic);
        int[] derivationPath = {44 | Bip32ECKeyPair.HARDENED_BIT, 60 | Bip32ECKeyPair.HARDENED_BIT, 0 | Bip32ECKeyPair.HARDENED_BIT, 0,0};
        Bip32ECKeyPair masterKeypair = Bip32ECKeyPair.generateKeyPair(MnemonicUtils.generateSeed(mnemonic, password));
        Bip32ECKeyPair  derivedKeyPair = Bip32ECKeyPair.deriveKeyPair(masterKeypair, derivationPath);
        Credentials credentials = Credentials.create(derivedKeyPair);

        t1.setText(credentials.getEcKeyPair().getPrivateKey().toString());
        t2.setText(credentials.getEcKeyPair().getPublicKey().toString());
















     String myadd1="0x57335538C8D4354c9de1B153674C6601ECE52Cfa";
        String myadd2="0xbc742f7bd7d16B8f667326C70299159396f191C1";
        String ptkey="633BC6EF07E8334213B032CCFAA11FD9CB04C823B8A137196D8D665C391B5CE0";
        Credentials credentials = Credentials.create(ptkey);
        EthGetTransactionCount ethGetTransactionCount;
        BigInteger nonce;
        BigInteger value;
        BigInteger gasLimit;
        BigInteger gasPrice;

        RawTransaction rawTransaction;
        byte[] signedMessage;
        EthSendTransaction ethSendTransaction;
        String transactionHash;

        try {
            ethGetTransactionCount=web3.ethGetTransactionCount(credentials.getAddress(),DefaultBlockParameterName.LATEST).sendAsync().get();
            nonce =  ethGetTransactionCount.getTransactionCount();
            t1.setText("Nonce is "+nonce.toString());
            value=Convert.toWei("1", Unit.ETHER).toBigInteger();
            t2.setText("Ammount "+value.toString());
            gasLimit = BigInteger.valueOf(21000);
            gasPrice = Convert.toWei("1", Unit.GWEI).toBigInteger();
            t3.setText("GasLimit & Gas Price "+gasLimit.toString()+" "+gasPrice.toString());
            rawTransaction=RawTransaction.createEtherTransaction(nonce, gasPrice, gasLimit, myadd2, value);

            signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
            String hexValue = Numeric.toHexString(signedMessage);
            t1.setText(hexValue);
            ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
            transactionHash = ethSendTransaction.getTransactionHash();
            t4.setText(transactionHash);




        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

RawTransaction rawTransaction;
        byte[] signedMessage;
        EthSendTransaction ethSendTransaction;
        String transactionHash;
