<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;

use DB;

class IncomeController extends Controller
{
    //
    public function getIn(Request $request) {
        $userid = $request->user_id;
        $income = Income::where(["user_id" => $userid])->get();
        if(isset($income))
        {
            return response()->json([
                'success'=>true,
                'income'=> $income,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'income'=> null,
            ]);
        }
    }

    public function getInByType(Request $request) {
        $userid = $request->user_id;
        $type = $request->intype_id;
        $income = Income::where(["user_id" => $userid, "intype_id" => $type])->get();
        if(isset($income))
        {
            return response()->json([
                'success'=>true,
                'income'=> $income,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'income'=> null,
            ]);
        }
    }

    public function getInDetail(Request $request) {
        $incomeid = $request->income_id;
        $income = Income::where(["income_id" => $incomeid])->first();
        $intype = Intype::where(["intype_id" => $income->intype_id])->first();
        if(isset($income))
        {
            return response()->json([
                'success'=>true,
                'income'=> $income,
                'intype'=> $intype,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'income'=> null,
                'intype'=> null,
            ]);
        }
    }

    public function getInDetailEdit(Request $request) {
        $incomeid = $request->income_id;
        $income = Income::where(["income_id" => $incomeid])->first();
        if(isset($income))
        {
            return response()->json([
                'success'=>true,
                'income'=> $income,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'income'=> null,
            ]);
        }
    }

    public function addIn(Request $request) {
        $income = new Income();

        $income->income_name = $request->income_name;
        $income->income_val = $request->income_val;
        $income->income_val_2 = $request->income_val_2;
        $income->income_valstring = $request->income_valstring;
        $income->intype_id = $request->intype_id;
        $income->income_day = $request->income_day;
        $income->income_month = $request->income_month;
        $income->income_year = $request->income_year;
        $income->user_id = $request->user_id;
        
        $income->save();

        $sum = DB::table('sumincome')
            ->where('user_id', $request->user_id)
            ->where('si_month', $request->income_month)
            ->where('si_year', $request->income_year)
            ->first();

        if ($sum)
        {
            $data = array();
            $data['si_val'] = $sum->si_val + $request->income_val;
            $data['si_val_2'] = $sum->si_val_2 + $request->income_val_2;
            DB::table('sumincome')
            ->where('user_id',$request->user_id)
            ->where('si_month',$request->income_month)
            ->where('si_year',$request->income_year)
            ->update($data);
        }
        else 
        {
            $data = array();
            $data['user_id'] = $request->user_id;
            $data['si_val'] = $request->income_val;
            $data['si_val_2'] = $request->income_val_2;
            $data['si_month'] = $request->income_month;
            $data['si_year'] = $request->income_year;
            DB::table('sumincome')->insert($data);
        }
        
        $list = Income::where(["user_id" => $request->user_id])->get();

        return response()->json([
            'success'=>true,
            'income'=> $list,
        ]);
    }

    public function delIn(Request $request) {
        $income_id = $request->income_id;

        $in = DB::table('incomes')->where('income_id',$income_id)->first();

        $sum = DB::table('sumincome')
            ->where('user_id', $in->user_id)
            ->where('si_month', $in->income_month)
            ->where('si_year', $in->income_year)
            ->first();

        $data = array();
        $data['si_val'] = $sum->si_val - $in->income_val;
        $data['si_val_2'] = $sum->si_val_2 - $in->income_val_2;

        DB::table('sumincome')
            ->where('user_id',$in->user_id)
            ->where('si_month',$in->income_month)
            ->where('si_year',$in->income_year)
            ->update($data);

        DB::table('incomes')->where('income_id',$income_id)->delete();

        $list = Income::where(["user_id" => $request->user_id])->get();

        return response()->json([
            'success'=>true,
            'income'=> $list,
        ]);
    }

    public function editIn(Request $request)
    {
        $data = array();
        $data['income_name'] = $request->income_name;
        $data['income_val'] = $request->income_val;
        $data['income_val_2'] = $request->income_val_2;
        $data['income_valstring'] = $request->income_valstring;
        $data['intype_id'] = $request->intype_id;
        $data['income_day'] = $request->income_day;
        $data['income_month'] = $request->income_month;
        $data['income_year'] = $request->income_year;
        $data['user_id'] = $request->user_id;

        $income_id = $request->income_id;

        DB::table('incomes')->where('income_id',$income_id)->update($data);
        $income = Income::where(["income_id" =>$income_id])->first();
        $intype = Intype::where(["intype_id" => $income->intype_id])->first();
        return response()->json([
            'success'=>true,
            'income'=> $income,
            'intype'=> $intype,
        ]);
    }
}
